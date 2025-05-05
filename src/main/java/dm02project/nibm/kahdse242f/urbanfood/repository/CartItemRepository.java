package dm02project.nibm.kahdse242f.urbanfood.repository;


import dm02project.nibm.kahdse242f.urbanfood.dto.CartItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CartItemRepository {

    @Autowired
    private DataSource dataSource;

    public List<CartItem> getCartItemsByUserId(Long userId) {
        List<CartItem> cartItems = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             CallableStatement cs = conn.prepareCall("{ call GetUserCartItems(?, ?) }")) {

            cs.setLong(1, userId);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(2);
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setCartId(rs.getLong("CART_ID"));
                item.setUserId(userId); // Set from parameter since it's not in the result
                item.setProductId(rs.getLong("PRODUCT_ID"));
                item.setProductName(rs.getString("product_name")); // Match the alias
                item.setPrice(rs.getDouble("PRICE"));
                item.setQuantity(rs.getInt("QUANTITY"));
                item.setImageMain(rs.getString("image_url"));
                cartItems.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    public String updateCartItem(Long cartId, Long userId, Long productId, Integer quantity) {
        String status = null;

        try (Connection conn = dataSource.getConnection();
             CallableStatement cs = conn.prepareCall("{ call UpdateCartItem(?, ?, ?, ?, ?) }")) {

            cs.setLong(1, cartId);
            cs.setLong(2, userId);
            cs.setLong(3, productId);
            cs.setInt(4, quantity);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.execute();

            status = cs.getString(5);

        } catch (SQLException e) {
            e.printStackTrace();
            status = "ERROR: " + e.getMessage();
        }

        return status;
    }

    //call get total procedure
    @PersistenceContext
    private EntityManager entityManager;

    public Map<String, Double> calculateCartTotal(Long userId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("CALCULATE_CART_TOTAL");

        // Set input parameter
        query.registerStoredProcedureParameter("p_user_id", Long.class, ParameterMode.IN);
        query.setParameter("p_user_id", userId);

        // Register output parameters
        query.registerStoredProcedureParameter("p_total", Double.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("p_vat", Double.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("p_shipping", Double.class, ParameterMode.OUT);

        query.execute();

        // Get the results
        Double total = (Double) query.getOutputParameterValue("p_total");
        Double vat = (Double) query.getOutputParameterValue("p_vat");
        Double shipping = (Double) query.getOutputParameterValue("p_shipping");

        Map<String, Double> result = new HashMap<>();
        result.put("total", total);
        result.put("vat", vat);
        result.put("shipping", shipping);

        return result;
    }

    public void removeCartItem(Long cartId) {
        try (Connection conn = dataSource.getConnection();
             CallableStatement cs = conn.prepareCall("{ call REMOVE_CART_ITEM(?) }")) {

            cs.setLong(1, cartId);
            cs.execute();

        } catch (SQLException e) {
            e.printStackTrace(); // You can log this properly for production
            throw new RuntimeException("Failed to remove cart item with ID: " + cartId, e);
        }
    }

    public int getCartItemCount1(Long userId) {
        int itemCount = 0;

        try (Connection conn = dataSource.getConnection();
             CallableStatement cs = conn.prepareCall("{ call GET_CART_ITEM_COUNT(?, ?) }")) {

            cs.setLong(1, userId);

            cs.registerOutParameter(2, Types.INTEGER);

            cs.execute();

            itemCount = cs.getInt(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemCount;
    }


}
