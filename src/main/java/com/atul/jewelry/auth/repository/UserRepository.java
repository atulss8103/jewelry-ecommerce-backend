package com.atul.jewelry.auth.repository;

import com.atul.jewelry.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    Optional<User> findById(UUID id);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

}