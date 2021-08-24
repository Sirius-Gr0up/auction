package SiriusGroup.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class GreetingController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message,Principal p) throws Exception {
        Thread.sleep(1000); // simulated delay
        String name=applicationUserRepository.findByUsername(p.getName()).getFirstName()+' '+applicationUserRepository.findByUsername(p.getName()).getLastName();
          ApplicationUser user =applicationUserRepository.findByUsername(p.getName());
          Greeting g=new Greeting(HtmlUtils.htmlEscape(message.getName())+" bid");
         g.setWinner(user.getFirstName() +' '+ user.getLastName());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        g.setNow(formatter.format(date));
//          if (!g.isParticipants(user)){
//              g.addParticipants(user);
//          }
          return g;
           // return g;

    }

    @GetMapping("/massage")
    public String getMassage(){
        return "massage.html";
    }
}
