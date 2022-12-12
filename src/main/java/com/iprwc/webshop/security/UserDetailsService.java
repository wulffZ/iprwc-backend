package com.iprwc.webshop.security;

import com.iprwc.webshop.model.User;
import com.iprwc.webshop.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Optional;

@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userResult = userRepository.findByEmail(email);
        if(userResult.isEmpty())
            throw new UsernameNotFoundException("Could not find user with the following email: " + email);
        User employee = userResult.get();
        return new org.springframework.security.core.userdetails.User(
                email,
                employee.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}

