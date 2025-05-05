package dm02project.nibm.kahdse242f.urbanfood.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void addToCart(Long userId, Long productId, Integer quantity) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("AddToCart")
                .registerStoredProcedureParameter("p_user_id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_product_id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_quantity", Integer.class, ParameterMode.IN)
                .setParameter("p_user_id", userId)
                .setParameter("p_product_id", productId)
                .setParameter("p_quantity", quantity); // can be null

        query.execute();
    }

}
