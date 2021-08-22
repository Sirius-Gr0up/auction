package SiriusGroup.auction;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    private String productName ;
    private String productImageUrl;
    private Date time;
    private int minPrice;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ApplicationUser getOwner() {
        return owner;
    }

    public void setOwner(ApplicationUser owner) {
        this.owner = owner;
    }

    @ManyToOne
    ApplicationUser owner;


    public Products(String productName, String productImageUrl, Date time, int minPrice ,ApplicationUser owner) {
        this.productName = productName;
        this.productImageUrl = productImageUrl;
        this.time = time;
        this.minPrice = minPrice;
        this.owner = owner;
    }
    public Products(String productName, String productImageUrl, Date time, int minPrice ) {
        this.productName = productName;
        this.productImageUrl = productImageUrl;
        this.time = time;
        this.minPrice = minPrice;

    }
    public Products() {

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
}
