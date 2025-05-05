package dm02project.nibm.kahdse242f.urbanfood.service;

import dm02project.nibm.kahdse242f.urbanfood.entity.Feedback;
import dm02project.nibm.kahdse242f.urbanfood.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
}