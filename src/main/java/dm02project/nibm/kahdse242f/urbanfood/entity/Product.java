package dm02project.nibm.kahdse242f.urbanfood.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTS", schema = "URBANFOOD")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "old_price")
    private BigDecimal oldPrice;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "image_main")
    private String imageMain;

    @Column(name = "image_gallery")
    @Lob
    private String imageGallery;

    @Column(name = "category")
    private String category;

    @Column(name = "badge")
    private String badge;

    // Constructors
    public Product() {}

    // Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getOldPrice() { return oldPrice; }
    public void setOldPrice(BigDecimal oldPrice) { this.oldPrice = oldPrice; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String getImageMain() { return imageMain; }
    public void setImageMain(String imageMain) { this.imageMain = imageMain; }

    public String getImageGallery() { return imageGallery; }
    public void setImageGallery(String imageGallery) { this.imageGallery = imageGallery; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getBadge() { return badge; }
    public void setBadge(String badge) { this.badge = badge; }
}