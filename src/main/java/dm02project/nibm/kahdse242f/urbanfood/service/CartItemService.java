package dm02project.nibm.kahdse242f.urbanfood.service;

import dm02project.nibm.kahdse242f.urbanfood.dto.CartItem;
import dm02project.nibm.kahdse242f.urbanfood.dto.CartUpdateRequest;
import dm02project.nibm.kahdse242f.urbanfood.entity.Product;
import dm02project.nibm.kahdse242f.urbanfood.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartRepository;

    public List<CartItem> getCartForUser(Long userId) {
        return cartRepository.getCartItemsByUserId(userId);
    }

    public void updateCartItems(Long userId, List<CartUpdateRequest.CartItemUpdate> items) {
        for (CartUpdateRequest.CartItemUpdate item : items) {
            String result = cartRepository.updateCartItem(
                    item.getCartId(),
                    userId,
                    item.getProductId(),
                    item.getQuantity()
            );

            if (result.startsWith("ERROR")) {
                throw new RuntimeException(result);
            }
        }
    }

    public void removeCartItem(Long cartId, Long userId) {
        cartRepository.updateCartItem(cartId, userId, null, 0);
    }
    //for
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> calculateCartTotal(Long userId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("CALCULATE_CART_TOTAL")
                .declareParameters(
                        new SqlOutParameter("p_total", Types.NUMERIC),
                        new SqlOutParameter("p_vat", Types.NUMERIC),
                        new SqlOutParameter("p_shipping", Types.NUMERIC)
                );

        Map<String, Object> out = jdbcCall.execute(Map.of("p_user_id", userId));
        return out;
    }

    public void removeCartItem(Long cartId) {
        cartRepository.removeCartItem(cartId);
    }

    public int getCartItemCount(Long userId) {
        // Call the repository method to get the cart item count
        return cartRepository.getCartItemCount1(userId);
    }

}
