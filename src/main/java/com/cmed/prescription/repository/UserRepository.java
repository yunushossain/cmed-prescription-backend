package com.cmed.prescription.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cmed.prescription.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
