package dm02project.nibm.kahdse242f.urbanfood.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AdminRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean validateAdminLogin(String username, String password) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("ADMIN_LOGIN_PROC");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_username", username);
        inParams.put("p_password", password);

        // OUT parameter registration
        jdbcCall.declareParameters(
                new SqlParameter("p_username", Types.VARCHAR),
                new SqlParameter("p_password", Types.VARCHAR),
                new SqlOutParameter("p_result", Types.INTEGER)
        );

        Map<String, Object> result = jdbcCall.execute(inParams);

        Number status = (Number) result.get("p_result");
        return status != null && status.intValue() > 0;
    }

}


