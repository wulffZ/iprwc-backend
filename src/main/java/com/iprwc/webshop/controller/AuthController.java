package com.iprwc.webshop.controller;

import com.iprwc.webshop.dto.UserStoreDTO;
import com.iprwc.webshop.model.User;
import com.iprwc.webshop.model.LoginCredentials;
import com.iprwc.webshop.model.Role;
import com.iprwc.webshop.repositories.UserRepository;
import com.iprwc.webshop.security.JWTUtilization;
import com.iprwc.webshop.service.EncryptionService;
import com.iprwc.webshop.service.RoleService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserRepository userRepo;
    private JWTUtilization jwtUtil;
    private AuthenticationManager authManager;
    private RoleService roleService;

    public AuthController(UserRepository userRepo, JWTUtilization jwtUtil, AuthenticationManager authManager, PasswordEncoder passEncoder, RoleService roleService) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.roleService = roleService;
    }

    @PostMapping("/register")
    public Map<String, Object> registrationHandler(@Valid @RequestBody UserStoreDTO userStoreDTO) throws Exception {
        User user = userStoreDTO.toUser();
        user.setPassword(EncryptionService.decrypt(user.getPassword()));

        List<Role> roles = new ArrayList<>();
        int userRoleIndex = 2;
        roles.add(this.roleService.getRoles().get(userRoleIndex));
        user.setRoles(roles);

        userRepo.save(user);
        String token = jwtUtil.generateToken(user.getEmail(), roleService.toJsonRoleString(user.getRoles()));
        return Collections.singletonMap("jwt_token", token);
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body) {
        try {
            String email = body.getEmail();
            String password = EncryptionService.decrypt(body.getPassword());

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
            authManager.authenticate(authToken);

            String token = jwtUtil.generateToken(body.getEmail(), body.getRoles());
            return Collections.singletonMap("jwt_token", token);
        } catch (AuthenticationException authExc) {
            throw new RuntimeException("Login credentials are invalid");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}