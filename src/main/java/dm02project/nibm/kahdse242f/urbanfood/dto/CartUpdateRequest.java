package dm02project.nibm.kahdse242f.urbanfood.dto;

import java.util.List;

public class CartUpdateRequest {
    private List<CartItemUpdate> items;

    // Getters and setters
    public List<CartItemUpdate> getItems() {
        return items;
    }

    public void setItems(List<CartItemUpdate> items) {
        this.items = items;
    }

    public static class CartItemUpdate {
        private Long cartId;
        private Long productId;
        private Integer quantity;

        public Long getCartId() {
            return cartId;
        }

        public void setCartId(Long cartId) {
            this.cartId = cartId;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        // Getters and setters

    }
}