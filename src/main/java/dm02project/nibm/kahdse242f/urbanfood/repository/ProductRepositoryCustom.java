/*package dm02project.nibm.kahdse242f.urbanfood.repository;

import dm02project.nibm.kahdse242f.urbanfood.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.dialect.OracleTypes;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> fetchProductsFromProcedure() {
        Session session = entityManager.unwrap(Session.class);

        return session.doReturningWork(connection -> {
            CallableStatement cs = connection.prepareCall("{ call FetchProductsToShopGrid(?) }");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);
            List<Product> products = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product();
                product.setName(rs.getString("name"));
                product.setPrice(BigDecimal.valueOf(rs.getDouble("price")));
                product.setOldPrice(BigDecimal.valueOf(rs.getDouble("old_price")));
                product.setImageMain(rs.getString("image_main"));
                product.setBadge(rs.getString("badge"));
                products.add(product);
            }

            return products;
        });
    }
}
*/