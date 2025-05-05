package dm02project.nibm.kahdse242f.urbanfood.controller;

import dm02project.nibm.kahdse242f.urbanfood.entity.ContactMessage;
import dm02project.nibm.kahdse242f.urbanfood.entity.User;
import dm02project.nibm.kahdse242f.urbanfood.service.ContactMessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contact")
public class ContactMessageController {

    @Autowired
    private ContactMessageService contactMessageService;

    @GetMapping
    public String showContactForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        ContactMessage contactMessage = new ContactMessage();
        if (loggedInUser != null) {
            contactMessage.setEmail(loggedInUser.getEmail()); // pre-fill email
        }

        model.addAttribute("contactMessage", contactMessage);
        return "contact"; // contact.html
    }

    @PostMapping("/submit")
    public String submitContactForm(@ModelAttribute ContactMessage contactMessage) {
        contactMessageService.saveMessage(contactMessage);
        return "redirect:/contact?success"; // Redirect to contact page after submitting
    }
}