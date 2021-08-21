package SiriusGroup.auction;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;


import javax.xml.crypto.Data;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;


@Controller
public class ApplicationUserController {


    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;



    @GetMapping("/")
    public String getHome(Principal p, Model model){
        try{


            model.addAttribute("UserInfo",applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get());
            System.out.println(applicationUserRepository.findByUsername(p.getName()).getImgUrl());
        }catch (NullPointerException e){

            model.addAttribute("userInfo","");

        }
        return "index.html";
    }

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup.html";
    }


    @GetMapping("/login")
    public String getSignInPage(){
        return "login.html";
    }

    @PostMapping("/signup")
    public RedirectView signUp(@RequestParam(value="username") String username, @RequestParam(value="password") String password, @RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="dateOfBirth") String dateOfBirth, @RequestParam(value="bio") String bio, @RequestParam(required=true,value="imageUrl") MultipartFile imageUrl) throws IOException {
//        ApplicationUser newUser = new ApplicationUser(username,bCryptPasswordEncoder.encode(password),firstName,lastName,dateOfBirth,bio,imageUrl);
//        applicationUserRepository.save(newUser);
        //Ensure that user registration also logs users into your app automatically.



        String fileName = StringUtils.cleanPath(imageUrl.getOriginalFilename());




        String uploadDir = "/Users/dawoodabuzahra/auction/auction/src/main/resources/static/img" ;

       String url= FileUploadUtil.saveFile(uploadDir, fileName, imageUrl);


        ApplicationUser newUser = new ApplicationUser(username,bCryptPasswordEncoder.encode(password),firstName,lastName,dateOfBirth,bio,url);
        applicationUserRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }


    @GetMapping("/profile")
    public String getUserProfile(Principal p, Model model){
        try{
            // for the header

            // for the body
            model.addAttribute("UserInfo",applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get());
            System.out.println(applicationUserRepository.findById(applicationUserRepository.findByUsername(p.getName()).getId()).get());
        }catch (NullPointerException e){
            model.addAttribute("userInfoe","");
            model.addAttribute("UserInfo",new ApplicationUser());
        }
        return "profile.html";
    }

}