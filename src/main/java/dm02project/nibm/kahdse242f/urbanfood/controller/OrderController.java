package dm02project.nibm.kahdse242f.urbanfood.controller;

import dm02project.nibm.kahdse242f.urbanfood.service.CartItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.CallableStatement;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/place-order")
    public String placeOrder(HttpSession session, @RequestParam Map<String, String> formData) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            // Redirect to login or show error
            return "redirect:/login";
        }

        // 1. Get total from cart service
        Map<String, Object> totals = cartItemService.calculateCartTotal(userId);
        Double totalAmount = ((Number) totals.get("p_total")).doubleValue();
        Double vatAmount = ((Number) totals.get("p_vat")).doubleValue();
        Double shippingAmount = ((Number) totals.get("p_shipping")).doubleValue();// ensure safe conversion
        Double grandTotal = totalAmount + vatAmount + shippingAmount;

        // 2. Build delivery address from form
        String address = formData.get("street") + ", " + formData.get("city") + ", " +
                formData.get("state") + " - " + formData.get("zip");

        // 3. Extract payment method
        String paymentMethod = formData.get("paymentMethod");

        // 4. Call the stored procedure to place the order
        jdbcTemplate.update(connection -> {
            CallableStatement cs = connection.prepareCall("{call URBANFOOD.place_order(?, ?, ?, ?)}");
            cs.setLong(1, userId);
            cs.setDouble(2, grandTotal);
            cs.setString(3, address);
            cs.setString(4, paymentMethod);
            return cs;
        });

        return "redirect:/success";
    }

}


