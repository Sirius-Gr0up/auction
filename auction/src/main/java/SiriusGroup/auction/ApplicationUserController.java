package SiriusGroup.auction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;


import javax.xml.crypto.Data;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;


@Controller
public class ApplicationUserController {


    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    ProductsRepository productsRepository;


    @GetMapping("/")
    public String getHome(Principal p, Model model) {
        try {
            model.addAttribute("UserInfo", applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get());
            System.out.println(applicationUserRepository.findByUsername(p.getName()).getImgUrl());
        } catch (NullPointerException e) {

            model.addAttribute("userInfo", "");

        }
        return "index.html";
    }

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup.html";
    }


    @GetMapping("/login")
    public String getSignInPage() {
        return "login.html";
    }

    @PostMapping("/signup")
    public RedirectView signUp(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName, @RequestParam(value = "dateOfBirth") String dateOfBirth, @RequestParam(value = "bio") String bio, @RequestParam(required = true, value = "imageUrl") MultipartFile imageUrl) throws IOException {
//        ApplicationUser newUser = new ApplicationUser(username,bCryptPasswordEncoder.encode(password),firstName,lastName,dateOfBirth,bio,imageUrl);
//        applicationUserRepository.save(newUser);
        //Ensure that user registration also logs users into your app automatically.


        String fileName = StringUtils.cleanPath(imageUrl.getOriginalFilename());



        System.out.println(fileName);


        System.out.println(fileName);


//        String uploadDir = "Users/S4C/auction/auction/src/main/resources/static/img";


//         String uploadDir = "/Users/Khalil/ASAC/401mid/auction/auction/src/main/resources/static/img" ;


        String uploadDir = "/Users/dawoodabuzahra/auction/auction/src/main/resources/static/img" ;
//        String uploadDir = "/Users/user/LTUC/auction/auction/src/main/resources/static/img" ;

        String url = FileUploadUtil.saveFile(uploadDir, fileName, imageUrl);


        ApplicationUser newUser = new ApplicationUser(username, bCryptPasswordEncoder.encode(password), firstName, lastName, dateOfBirth, bio, url);
        applicationUserRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }


    @GetMapping("/profile")

    public String getUserProfile(Principal p, Model model){
        try{

            model.addAttribute("UserInfo",applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get());

            System.out.println(applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get());
        } catch (NullPointerException e) {
            model.addAttribute("userInfoe", "");
            model.addAttribute("UserInfo", new ApplicationUser());
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass()))
            return "login.html";
        else
            return "profile.html";
    }

//
//    @GetMapping("/product" )
//    public String getProduct(Principal p, Model model){
//        model.addAttribute("UserInfo",applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get());
//
//        return "product.html";
//    }

    @PostMapping("/editUser")
    public RedirectView editUser(Principal p,@RequestParam(value="username") String username, @RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="dateOfBirth") String dateOfBirth, @RequestParam(value="bio") String bio, @RequestParam(required=true,value="imageUrl") MultipartFile imageUrl) throws IOException{

        String fileName = StringUtils.cleanPath(imageUrl.getOriginalFilename());


        String uploadDir = "/Users/dawoodabuzahra/auction/auction/src/main/resources/static/img" ;
//        String uploadDir = "/Users/Khalil/ASAC/401mid/auction/auction/src/main/resources/static/img" ;
//        String uploadDir = "/Users/user/LTUC/auction/auction/src/main/resources/static/img" ;

        String url= FileUploadUtil.saveFile(uploadDir, fileName, imageUrl);

        applicationUserRepository.findByUsername(p.getName()).setFirstName(firstName);
        applicationUserRepository.findByUsername(p.getName()).setLastName(lastName);
        applicationUserRepository.findByUsername(p.getName()).setDateOfBirth(dateOfBirth);
        applicationUserRepository.findByUsername(p.getName()).setBio(bio);
        applicationUserRepository.findByUsername(p.getName()).setImgUrl(url);
        applicationUserRepository.save(applicationUserRepository.findByUsername(p.getName()));

        return new RedirectView("/profile");

    }


    //rawan














































    ////////////////////////////////////////


    //mohammad














































    ////////////////////////////////////////




}



