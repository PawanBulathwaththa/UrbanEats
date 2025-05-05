package dm02project.nibm.kahdse242f.urbanfood.controller;

import dm02project.nibm.kahdse242f.urbanfood.entity.Feedback;
import dm02project.nibm.kahdse242f.urbanfood.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@CrossOrigin(origins = "*") // Allow frontend JS to access backend
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public Feedback submitFeedback(@RequestBody Feedback feedback) {
        return feedbackService.saveFeedback(feedback);
    }

    @GetMapping
    public List<Feedback> getLatestFiveFeedbacks() {
        List<Feedback> allFeedbacks = feedbackService.getAllFeedbacks();

        // Sort newest first and limit to 5
        return allFeedbacks.stream()
                .sorted((f1, f2) -> f2.getId().compareTo(f1.getId())) // Reverse order by ID (MongoDB's _id has timestamp)
                .limit(5)
                .toList();
    }

}