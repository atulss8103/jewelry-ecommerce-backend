package com.atul.jewelry.cart.repository;

import com.atul.jewelry.auth.entity.User;
import com.atul.jewelry.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {

    Optional<Cart> findByUser(User user);

}