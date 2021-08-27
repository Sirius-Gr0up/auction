package SiriusGroup.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

import javax.transaction.Transactional;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
public class GreetingController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    GreetingRepository greetingRepository;

    @Autowired
    ProductsRepository productsRepository;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message,Principal p) throws Exception {
        Thread.sleep(1000); // simulated delay
          ApplicationUser user =applicationUserRepository.findByUsername(p.getName());
          Products currentProduct=productsRepository.findById(message.getProductId()).get();
          Greeting g=new Greeting(HtmlUtils.htmlEscape(message.getName())+" JOD" );
         g.setWinner(user.getFirstName() +' '+ user.getLastName());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        g.setNow(formatter.format(date));

          return g;
    }

    @GetMapping("/massage")
    public String getMassage(){
        return "massage.html";
    }
}
