package com.iprwc.webshop.dao;

import com.iprwc.webshop.model.User;
import com.iprwc.webshop.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDAO {

    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Returns an employee object with a specific id.
     * If there is no employee with the specified id, returns null
     *
     * @param id the id of the employee
     * @return employee
     */
    public User show(Integer id) {
        Optional<User> employee = this.userRepository.findById(id);

        return employee.orElse(null);
    }

    public User getUserDetails(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(email).get();
    }

    public User store(User user){
        return userRepository.save(user);
    }

    public User update(User user) {
        this.userRepository.update(user.getName(),  user.getEmail(), user.getPassword(), user.getId());
        return user;
    }
}