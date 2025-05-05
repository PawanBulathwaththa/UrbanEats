package dm02project.nibm.kahdse242f.urbanfood.repository;


import dm02project.nibm.kahdse242f.urbanfood.entity.ProductReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends MongoRepository<ProductReview, String> {


    List<ProductReview> findByProductIdOrderByCreatedAtDesc(Long productId);
}