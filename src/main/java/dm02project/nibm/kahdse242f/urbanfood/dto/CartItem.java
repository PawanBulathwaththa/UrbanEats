package dm02project.nibm.kahdse242f.urbanfood.dto;

public class CartItem {
    private Long cartId;
    private Long userId;
    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private String imageMain;
    private String imageGallery;

    // Getters and Setters

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImageMain() {
        return imageMain;
    }

    public void setImageMain(String imageMain) {
        this.imageMain = imageMain;
    }

    public String getImageGallery() {
        return imageGallery;
    }

    public void setImageGallery(String imageGallery) {
        this.imageGallery = imageGallery;
    }
}
