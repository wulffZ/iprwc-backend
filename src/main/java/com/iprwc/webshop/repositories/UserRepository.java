package com.iprwc.webshop.repositories;

import com.iprwc.webshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.name = :username, u.email = :email, u.password = :password WHERE u.id = :user_id")
    void update(String username, String email, String password, int user_id);

}