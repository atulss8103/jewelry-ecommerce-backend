package com.atul.jewelry.cart.repository;

import com.atul.jewelry.cart.entity.Cart;
import com.atul.jewelry.cart.entity.CartItem;
import com.atul.jewelry.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

}