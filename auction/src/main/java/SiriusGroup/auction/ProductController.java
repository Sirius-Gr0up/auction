package SiriusGroup.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping("/product" )
    public String getProduct(Principal p, Model model){
        model.addAttribute("UserInfo",applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get());
//        List<Products> products =  ProductsRepository.findProductById(applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get().getId());
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
//        List<Products> products =  ProductsRepository.findProductById((user).getId());
//        Products products1 =  productsRepository.findAll();
//        model.addAttribute("user",user);
        model.addAttribute("products", productsRepository.findAll());

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
                                     @RequestParam String productImageUrl,
                                     @RequestParam String time,
                                     @RequestParam int minPrice) throws ParseException {
        System.out.println("id: "+id);
        System.out.println("time"+time);
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(time);
        System.out.println("date1"+date1);
        ApplicationUser applicationUser = applicationUserRepository.findById(id).get();
        Products products=new Products(productName,productImageUrl,date1,minPrice,applicationUser);
//        ApplicationUser user = applicationUserRepository.findByUsername(principal.getName());
        productsRepository.save(products);
        return new RedirectView ("/product");
    }




    //rawan














































    ////////////////////////////////////////

    //mohammad













































    ////////////////////////////////////////

    //khalil














































    ////////////////////////////////////////

    //dawood














































    ////////////////////////////////////////


}
