package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.model.User;
import pl.coderslab.model.UserRole;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    List<User> findUsersByRoles(UserRole role);
    User findUserById(Long id);

}
