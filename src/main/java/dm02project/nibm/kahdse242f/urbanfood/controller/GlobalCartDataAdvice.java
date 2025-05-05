package dm02project.nibm.kahdse242f.urbanfood.controller;
import dm02project.nibm.kahdse242f.urbanfood.dto.CartItem;
import dm02project.nibm.kahdse242f.urbanfood.service.CartItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalCartDataAdvice {

    @Autowired
    private CartItemService cartService;

    @ModelAttribute
    public void addCartDataToModel(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId != null) {
            List<CartItem> cartItems = cartService.getCartForUser(userId);
            Map<String, Object> totals = cartService.calculateCartTotal(userId);

            model.addAttribute("globalCartItems", cartItems);
            model.addAttribute("globalCartSubtotal", totals.get("p_total"));
        }
    }
}
