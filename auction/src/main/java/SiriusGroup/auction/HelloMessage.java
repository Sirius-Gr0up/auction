package SiriusGroup.auction;

public class HelloMessage {

    private String name;

    private Long productId;
    public HelloMessage() {
    }

    public HelloMessage(String name) {
        this.name = name;
    }

    public HelloMessage(String name, Long productId) {
        this.name = name;
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


