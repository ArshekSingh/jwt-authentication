package com.example.jwtauthentication.repository;

import com.example.jwtauthentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsernameIgnoreCase(String username);

}
