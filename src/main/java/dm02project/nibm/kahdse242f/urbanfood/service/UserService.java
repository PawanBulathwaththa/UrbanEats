package dm02project.nibm.kahdse242f.urbanfood.service;

import dm02project.nibm.kahdse242f.urbanfood.entity.User;
import dm02project.nibm.kahdse242f.urbanfood.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public void registerUser(User user) {
        userRepository.save(user);
    }
}