package dm02project.nibm.kahdse242f.urbanfood.controller;

import dm02project.nibm.kahdse242f.urbanfood.dto.CartItem;
import dm02project.nibm.kahdse242f.urbanfood.dto.CartUpdateRequest;
import dm02project.nibm.kahdse242f.urbanfood.service.CartItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartItemController {

    @Autowired
    private CartItemService cartService;

    @GetMapping
    public String showCart(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        List<CartItem> cartItems = cartService.getCartForUser(userId);
        model.addAttribute("cartItems", cartItems);

        // Add cart totals
        Map<String, Object> totals = cartService.calculateCartTotal(userId);
        model.addAttribute("cartSubtotal", totals.get("p_total"));
        model.addAttribute("cartVat", totals.get("p_vat"));
        model.addAttribute("cartShipping", totals.get("p_shipping"));

        return "cart";
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> updateCart(@RequestBody CartUpdateRequest request, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            response.put("success", false);
            response.put("message", "User not logged in");
            return response;
        }

        try {
            cartService.updateCartItems(userId, request.getItems());
            response.put("success", true);
            response.put("message", "Cart updated successfully");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }

        return response;
    }

    @GetMapping("/remove/{cartId}")
    public String removeCartItem(@PathVariable Long cartId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        cartService.removeCartItem(cartId);
        return "redirect:/cart";
    }

    @GetMapping("/cart/{userId}")
    public String getCart(@PathVariable Long userId, Model model, HttpSession session) {
        // Call the service to get the item count for the given user
        int itemCount = cartService.getCartItemCount(userId);

        // Store item count in session
        session.setAttribute("itemCount", itemCount);

        model.addAttribute("itemCount", itemCount);  // Pass the item count to the model for this page
        return "index"; // Return the Thymeleaf template name (cart.html)
    }

}