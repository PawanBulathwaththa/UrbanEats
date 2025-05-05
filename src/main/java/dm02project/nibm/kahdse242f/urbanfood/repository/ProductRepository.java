package dm02project.nibm.kahdse242f.urbanfood.repository;

import dm02project.nibm.kahdse242f.urbanfood.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall getProductsByCategoryProcedure;

    @Autowired
    private JdbcTemplate jdbcTemplate1;
    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    private DataSource dataSource;

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        // Initialize the SimpleJdbcCall for the stored procedure
        getProductsByCategoryProcedure = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("URBANFOOD") // Use schemaName instead of catalogName
                .withProcedureName("GET_PRODUCTS_BY_CATEGORY")
                .declareParameters(
                        new org.springframework.jdbc.core.SqlParameter("p_category", java.sql.Types.VARCHAR),
                        new org.springframework.jdbc.core.SqlOutParameter("p_products", oracle.jdbc.OracleTypes.CURSOR,
                                new ProductRowMapper())
                );
    }

    public List<Product> getProductsByCategory(String category) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_category", category);

        Map<String, Object> result = getProductsByCategoryProcedure.execute(parameterSource);

        @SuppressWarnings("unchecked")
        List<Product> products = (List<Product>) result.get("p_products");

        return products;
    }

    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setProductId(rs.getLong("product_id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setOldPrice(rs.getBigDecimal("old_price"));
            product.setStock(rs.getInt("stock"));
            product.setImageMain(rs.getString("image_main"));
            product.setImageGallery(rs.getString("image_gallery"));
            product.setCategory(rs.getString("category"));
            product.setBadge(rs.getString("badge"));
            return product;
        }
    }

    @PostConstruct
    public void init1() {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate1)
                .withProcedureName("GET_ALL_PRODUCTS")
                .declareParameters(new SqlOutParameter("p_products", oracle.jdbc.OracleTypes.CURSOR,
                        (rs, rowNum) -> mapRowToProduct(rs)));
    }

    public List<Product> getAllProducts() {
        Map<String, Object> result = simpleJdbcCall.execute();
        return (List<Product>) result.get("p_products");
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

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public void updateProduct(Product product) {
        jdbcTemplate.update(
                "CALL UPDATE_PRODUCT(?, ?, ?, ?, ?, ?, ?, ?)",
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getOldPrice(),
                product.getStock(),
                product.getCategory(),
                product.getBadge()
        );
    }

    public void deleteProductById(Long productId) {
        jdbcTemplate.update("CALL DELETE_PRODUCT_BY_ID(?)", productId);
    }

}