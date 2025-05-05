package dm02project.nibm.kahdse242f.urbanfood.repository;

import dm02project.nibm.kahdse242f.urbanfood.entity.Product;
import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall getOrdersProcedure;

    @Autowired
    private DataSource dataSource;

    // Get all orders
    @PostConstruct
    public void init2() {
        getOrdersProcedure = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("GET_ALL_PRODUCTS")
                .declareParameters(new SqlOutParameter("p_orders", oracle.jdbc.OracleTypes.CURSOR,
                        (rs, rowNum) -> mapRowToProduct(rs)));
    }

    public List<Product> getAllProducts() {
        Map<String, Object> result = getOrdersProcedure.execute();
        return (List<Product>) result.get("p_orders");
    }

    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getLong("product_id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(BigDecimal.valueOf(rs.getDouble("price")));
        product.setOldPrice(BigDecimal.valueOf(rs.getDouble("old_price")));
        product.setStock(rs.getInt("stock"));
        product.setImageMain(rs.getString("image_main"));
        product.setImageGallery(rs.getString("image_gallery"));
        product.setCategory(rs.getString("category"));
        product.setBadge(rs.getString("badge"));
        return product;
    }

    // Get order items by order ID
    public List<Map<String, Object>> getOrderItemsByOrderId(int orderId) throws SQLException {
        List<Map<String, Object>> items = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall("{ call GET_ORDER_ITEMS_BY_ORDER_ID(?, ?) }");
        callableStatement.setInt(1, orderId);
        callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

        callableStatement.execute();
        ResultSet rs = (ResultSet) callableStatement.getObject(2);

        while (rs.next()) {
            Map<String, Object> item = new HashMap<>();
            item.put("itemId", rs.getInt("ITEM_ID")); // assuming column name
            item.put("productId", rs.getInt("PRODUCT_ID"));
            item.put("quantity", rs.getInt("QUANTITY"));
            item.put("price", rs.getDouble("PRICE"));
            item.put("orderId", rs.getInt("ORDER_ID"));
            items.add(item);
        }

        rs.close();
        callableStatement.close();
        connection.close();

        return items;
    }
}
