package SiriusGroup.auction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String bio;
    private String imgUrl;


    @ManyToMany
    @JoinTable(name ="wishlist",
            joinColumns= { @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false)},
            inverseJoinColumns= {@JoinColumn(name = "Products_id", referencedColumnName = "id", nullable = false)}
    )
    private List<Products> wishlist = new ArrayList<>();

    @OneToMany( fetch=FetchType.EAGER,mappedBy = "owner",cascade=CascadeType.ALL)
    @JsonIgnore
    List<Products> products;



    @ManyToMany
    @JoinTable(name ="by",
            joinColumns= { @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false)},
            inverseJoinColumns= {@JoinColumn(name = "Products_id", referencedColumnName = "id", nullable = false)}
    )
    private List<Products> by = new ArrayList<>();

    @OneToMany( fetch=FetchType.EAGER,mappedBy = "owner",cascade=CascadeType.ALL)
    @JsonIgnore
    List<Products> bay;

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio, String imgUrl) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.imgUrl = imgUrl;

    }

    public ApplicationUser(){}

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<Products> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<Products> wishlist) {
        this.wishlist = wishlist;
    }

    public void addUserToWishlist(Products products){
        this.wishlist.add(products);
    }
    public void removeUserFromWishlist(Products products){
        this.wishlist.remove(products);
    }
    public Boolean isWishlist(Products products){
        return this.wishlist.contains(products);
    }


    public void setBy(List<Products> by) {
        this.by = by;
    }

    public void addUserToBy(Products bay){
        this.by.add(bay);
    }
    public void removeUserFromBy(Products bay){
        this.by.remove(bay);
    }
    public Boolean isBy(Products bay){
        return this.by.contains(bay);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getNow(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
