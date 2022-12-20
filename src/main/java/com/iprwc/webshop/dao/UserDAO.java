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
     * Returns a user object with a specific id.
     * If there is no user with the specified id, returns null
     *
     * @param id the id of the user
     * @return user
     */
    public User show(Integer id) {
        Optional<User> user = this.userRepository.findById(id);

        return user.orElse(null);
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