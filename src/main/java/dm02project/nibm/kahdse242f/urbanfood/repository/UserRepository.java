package dm02project.nibm.kahdse242f.urbanfood.repository;

import dm02project.nibm.kahdse242f.urbanfood.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    User findByEmail(String email);
    User save(User user);
}