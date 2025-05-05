package dm02project.nibm.kahdse242f.urbanfood.controller;

import dm02project.nibm.kahdse242f.urbanfood.entity.User;
import dm02project.nibm.kahdse242f.urbanfood.repository.UserRepository;
import dm02project.nibm.kahdse242f.urbanfood.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User()); // This is important
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user,
                                  @RequestParam("confirmpassword") String confirmPassword,
                                  Model model) {
        if (userService.isUsernameTaken(user.getUsername())) {
            model.addAttribute("usernameError", "Username is already taken");
            return "register"; // Return to register page with error
        }

        // Check if passwords match
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("passwordError", "Passwords do not match");
            return "register"; // Return to register page with error
        }

        userService.registerUser(user);
        return "redirect:/login"; // Redirect to login page after successful registration
    }

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {

        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            // Store user in session
            session.setAttribute("loggedInUser", user);
            session.setAttribute("userId", user.getUserId());
            return "redirect:/"; // redirect to a page after login
        } else {
            model.addAttribute("loginError", "Invalid email or password");
            return "login";
        }
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}