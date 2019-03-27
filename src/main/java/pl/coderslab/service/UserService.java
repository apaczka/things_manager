package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.coderslab.model.User;
import pl.coderslab.model.UserRole;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.repository.UserRoleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";



    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void addWithDefaultRole(User user) {
        UserRole defaultRole = userRoleRepository.findByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);
    }

    public void addWithAdminRole(User user) {
        UserRole defaultRole = userRoleRepository.findByRole(ADMIN_ROLE);
        user.getRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);
    }
    public List<User> showAllUsers(UserRole role){
        List<User> users = new ArrayList<>();
        users = userRepository.findUsersByRoles(role);
        return users;
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void removeUser(Long id){
        userRepository.delete(id);
    }
    
    public User findUserById(Long id){ return userRepository.findOne(id);}
    public UserRole findRole(Long id){ return userRoleRepository.findById(id);}

    public UserRole findRole(String name){
       return userRoleRepository.findByRole(name);
        }
    }

