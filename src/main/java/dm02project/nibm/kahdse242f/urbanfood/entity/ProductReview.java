package dm02project.nibm.kahdse242f.urbanfood.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "product_reviews")
public class ProductReview {

    @Id
    private String id;

    private Long productId;

    private String username;

    private String email;

    private String comment;

    private Integer rating;

    @CreatedDate
    private Date createdAt;

    // Constructors
    public ProductReview() {}

    public ProductReview(Long productId, String username, String email, String comment, Integer rating) {
        this.productId = productId;
        this.username = username;
        this.email = email;
        this.comment = comment;
        this.rating = rating;
        this.createdAt = new Date();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}