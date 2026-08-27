package com.poketome.useridentityservice.repository;

import com.poketome.useridentityservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Spring magically turns these method names into SQL queries!
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}