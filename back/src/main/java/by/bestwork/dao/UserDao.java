package by.bestwork.dao;

import by.bestwork.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, String> {

    User findByEmail(String email);

    Optional<User> findById(Long id);
}
