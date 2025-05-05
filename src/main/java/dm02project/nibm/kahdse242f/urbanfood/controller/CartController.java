package dm02project.nibm.kahdse242f.urbanfood.controller;

import dm02project.nibm.kahdse242f.urbanfood.entity.User;
import dm02project.nibm.kahdse242f.urbanfood.repository.CartRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam(value = "quantity", required = false) Integer quantity,
                            HttpSession session) {

        // Retrieve logged-in user from session
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Long userId = loggedInUser.getUserId(); // Extract userId from session user
            cartRepository.addToCart(userId, productId, quantity);
        } else {
            // Redirect to login if user not found in session
            return "redirect:/login";
        }

        return "redirect:/"; // or any page after adding to cart
    }


}