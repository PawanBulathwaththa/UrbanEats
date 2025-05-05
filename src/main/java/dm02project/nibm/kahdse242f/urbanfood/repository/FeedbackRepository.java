package dm02project.nibm.kahdse242f.urbanfood.repository;
import dm02project.nibm.kahdse242f.urbanfood.entity.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {
}