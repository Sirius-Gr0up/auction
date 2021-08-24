package SiriusGroup.auction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String winner;
    private String now;
    @ManyToOne
    private Products bidingProduct;

//    private List<ApplicationUser>participants=new ArrayList<ApplicationUser>();


    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }


    public Greeting(String content, String winner) {
        this.content = content;
        this.winner = winner;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public String getContent() {
        return content;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

//    public List<ApplicationUser> getParticipants() {
//        return participants;
//    }

//    public void setParticipants(List<ApplicationUser> participants) {
//        this.participants = participants;
//    }
//    public boolean isParticipants(ApplicationUser user){
//        return this.participants.contains(user);
//    }
//    public void addParticipants(ApplicationUser user){
//        this.participants.add(user);
//    }

    public Long getId() {
        return id;
    }

    public Products getBidingProduct() {
        return bidingProduct;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBidingProduct(Products bidingProduct) {
        this.bidingProduct = bidingProduct;
    }
    
}
