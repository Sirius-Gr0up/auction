package SiriusGroup.auction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dis;

//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private Date createdAt;

    private String productName ;
    private String productImageUrl;
    private String secondImage;

    private String thirdImage;

    private Date time;
    private int minPrice;
    private int maxPrice;
    private int currentPrice;


    @ManyToOne
    ApplicationUser owner;


    @ManyToMany(mappedBy = "wishlist")
    private List<ApplicationUser> curUser;

    @OneToMany(fetch=FetchType.EAGER,mappedBy = "bidingProduct", cascade = CascadeType.ALL)
    private List<Greeting> biding=new ArrayList<>();

    public Products(String dis, String productName, String productImageUrl, Date time, int minPrice, int maxPrice,String secondImage,String thirdImage, ApplicationUser owner) {
        this.dis = dis;
        this.productName = productName;
        this.productImageUrl = productImageUrl;
        this.time = time;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.owner = owner;
        this.currentPrice=minPrice;
        this.secondImage=secondImage;
        this.thirdImage=thirdImage;

    }

    public Products(String productName, String productImageUrl, Date time, int minPrice , ApplicationUser owner) {
        this.productName = productName;
        this.productImageUrl = productImageUrl;
        this.time = time;
        this.minPrice = minPrice;
        this.owner = owner;
        this.currentPrice=minPrice;
    }

    public Products(String productName, String productImageUrl, Date time, int minPrice ) {
        this.productName = productName;
        this.productImageUrl = productImageUrl;
        this.time = time;
        this.minPrice = minPrice;
        this.currentPrice=minPrice;

    }
    public Products() {

    }
    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getDis() {
        return dis;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public ApplicationUser getOwner() {
        return owner;
    }

    public void setOwner(ApplicationUser owner) {
        this.owner = owner;
    }

    public String getSecondImage() {
        return secondImage;
    }

    public void setSecondImage(String secondImage) {
        this.secondImage = secondImage;
    }

    public String getThirdImage() {
        return thirdImage;
    }

    public void setThirdImage(String thirdImage) {
        this.thirdImage = thirdImage;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public List<Greeting> getBiding() {
        return biding;
    }

    public void setBiding(List<Greeting> biding) {
        this.biding = biding;
    }
    public void addbiding(Greeting g){
        this.biding.add(g);
    }

    public List<ApplicationUser> getCurUser() {

        return curUser;
    }

    public void setCurUser(List<ApplicationUser> curUser) {
        this.curUser = curUser;
    }
}
