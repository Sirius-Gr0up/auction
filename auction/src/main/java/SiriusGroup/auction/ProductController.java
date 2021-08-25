package SiriusGroup.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.HtmlUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller

public class ProductController {

        @Autowired
        ApplicationUserRepository applicationUserRepository;
        @Autowired
        ProductsRepository productsRepository;

        @Autowired
        GreetingRepository greetingRepository;

    @GetMapping("/product" )
    public String getProduct(Principal p, Model model){
        model.addAttribute("UserInfo",applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get());
//        List<Products> products =  ProductsRepository.findProductById(applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get().getId());
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
//        List<Products> products =  ProductsRepository.findProductById((user).getId());
//        Products products1 =  productsRepository.findAll();
//        model.addAttribute("user",user);
        model.addAttribute("products", productsRepository.findAll());
//        model.addAttribute("isWishlist",applicationUserRepository.findByUsername(p.getName()).isWishlist());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass()))
            return "login.html";
        else
            return "product.html";

    }

    @GetMapping("/pro")
    public String pro(Principal principal, Model model) {
        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userId", user.getId());
        System.out.println("dddddddddddddd"+user.getId());
        return "pro.html";
    }
    @PostMapping("/product/{id}")
    public RedirectView postProduct (
                                        @PathVariable Long id,
                                     @RequestParam String productName,
                                     @RequestParam MultipartFile productImageUrl,
                                     @RequestParam MultipartFile secondImage,
                                     @RequestParam MultipartFile thirdImage,
                                     @RequestParam String time,
                                     @RequestParam int maxPrice,
                                     @RequestParam String dis,
                                     @RequestParam int minPrice) throws ParseException, IOException {


        String fileName = StringUtils.cleanPath(productImageUrl.getOriginalFilename());
        String uploadDir = "/Users/user/LTUC/auction/auction/src/main/resources/static/img" ;
//        String uploadDir = "/Users/dawoodabuzahra/401/auction/auction/src/main/resources/static/img" ;
//        String uploadDir = "/Users/Khalil/ASAC/auction/auction/src/main/resources/static/img";
        String url = FileUploadUtil.saveFile(uploadDir, fileName, productImageUrl);


        String fileName2 = StringUtils.cleanPath(secondImage.getOriginalFilename());


        String url2 = FileUploadUtil.saveFile(uploadDir, fileName2, secondImage);


        String fileName3 = StringUtils.cleanPath(thirdImage.getOriginalFilename());


        String url3 = FileUploadUtil.saveFile(uploadDir, fileName3, thirdImage);

        System.out.println("id: "+id);
        System.out.println("time"+time);
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(time);
        System.out.println("date1"+date1);
        ApplicationUser applicationUser = applicationUserRepository.findById(id).get();
        Products products=new Products( dis, productName,url,date1,minPrice, maxPrice,url2,url3,applicationUser);
//        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        productsRepository.save(products);
        return new RedirectView ("/product");
    }

    @GetMapping("/singleProduct/{id}")
    public String getSingleProduct(@PathVariable Long id,Model m,Principal p){

        Products product=productsRepository.findById(id).get();

        m.addAttribute("UserInfo", applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get());
        m.addAttribute("product",product);

        return "singleProduct.html";

    }




    //rawan














































    ////////////////////////////////////////

    //mohammad
    @Transactional
@PostMapping("/addBid/{id}")
public RedirectView addBid (@PathVariable Long id,@RequestParam int vol,Principal p){
    ApplicationUser user =applicationUserRepository.findByUsername(p.getName());
    Products products=productsRepository.findById(id).get();
    int value =vol  +  products.getCurrentPrice();
    products.setCurrentPrice(value);
        Products currentProduct=productsRepository.findById(id).get();
        Greeting g=new Greeting(HtmlUtils.htmlEscape(vol+"")+" JOD" );
        g.setBidingProduct(currentProduct);
        g.setWinner(user.getFirstName() +' '+ user.getLastName());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        g.setNow(formatter.format(date));
    productsRepository.save(products);
        greetingRepository.save(g);
return new RedirectView("/singleProduct/"+id);
}




















    @GetMapping ("/wishlist")
    public String getAllUsers(Model m,Principal p){
        m.addAttribute("UserInfo", applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get());

        return "wishlist.html";
    }

    @GetMapping("/wish/{id}")
    public RedirectView followUser(Principal p,@PathVariable Long id){
        ApplicationUser getUser=applicationUserRepository.findByUsername(p.getName());
//        productsRepository.findById(id).get().setIsWished(true);
        Products addProduts=productsRepository.findById(id).get();
        getUser.addUserToWishlist(addProduts);
        applicationUserRepository.save(getUser);
        return new RedirectView("/product");
    }


    @GetMapping("/deleteWish/{id}")
    public RedirectView unfollowUser(Principal p,@PathVariable Long id){
        ApplicationUser getUser=applicationUserRepository.findByUsername(p.getName());
        Products removeList=productsRepository.findById(id).get();
        getUser.removeUserFromWishlist(removeList);
        applicationUserRepository.save(getUser);
       return new RedirectView("/wishlist");
    }















    ////////////////////////////////////////

    //khalil
    @GetMapping("/myProducts")
    public String getMyProducts(Model m,Principal p){
        m.addAttribute("UserInfo", applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get());
        m.addAttribute("products",applicationUserRepository.findByUsername(p.getName()).getProducts());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass()))
            return "login.html";
        else
            return "myProducts.html";

    }

    @GetMapping("/myProducts/{id}")
    public RedirectView deleteProduct(@PathVariable Long id) {
        Products p1=productsRepository.findById(id).get();
        productsRepository.deleteById(p1.getId());

        return new RedirectView("/myProducts");
    }













































    ////////////////////////////////////////

    //dawood














































    ////////////////////////////////////////


}
