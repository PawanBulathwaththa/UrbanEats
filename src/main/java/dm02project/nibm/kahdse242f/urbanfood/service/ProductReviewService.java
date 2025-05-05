package dm02project.nibm.kahdse242f.urbanfood.service;

import dm02project.nibm.kahdse242f.urbanfood.entity.ProductReview;
import dm02project.nibm.kahdse242f.urbanfood.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductReviewService {

    @Autowired
    private ProductReviewRepository productReviewRepository;


    public void saveReview(ProductReview review) {
        productReviewRepository.save(review);
    }


    public List<ProductReview> getReviewsForProduct(Long productId) {
        return productReviewRepository.findByProductIdOrderByCreatedAtDesc(productId);
    }
}