package dm02project.nibm.kahdse242f.urbanfood.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feedbacks")
public class Feedback {

    @Id
    private String id;
    private String name;
    private String message;

    // Constructors
    public Feedback() {}

    public Feedback(String name, String message) {
        this.name = name;
        this.message = message;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}