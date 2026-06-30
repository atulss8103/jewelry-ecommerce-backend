package com.atul.jewelry.cart.controller;

import com.atul.jewelry.auth.entity.User;
import com.atul.jewelry.auth.repository.UserRepository;
import com.atul.jewelry.cart.dto.request.AddToCartRequest;
import com.atul.jewelry.cart.dto.request.UpdateQuantityRequest;
import com.atul.jewelry.cart.dto.response.CartResponse;
import com.atul.jewelry.cart.service.CartService;
import com.atul.jewelry.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    private User getUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    @PostMapping("/add/{userId}")
    public CartResponse addToCart(
            @PathVariable UUID userId,
            @Valid @RequestBody AddToCartRequest request) {

        return cartService.addToCart(getUser(userId), request);
    }

    @GetMapping("/{userId}")
    public CartResponse getCart(@PathVariable UUID userId) {

        return cartService.getCart(getUser(userId));
    }

    @PutMapping("/update/{userId}")
    public CartResponse updateQuantity(
            @PathVariable UUID userId,
            @Valid @RequestBody UpdateQuantityRequest request) {

        return cartService.updateQuantity(getUser(userId), request);
    }

    @DeleteMapping("/item/{userId}/{cartItemId}")
    public String removeItem(
            @PathVariable UUID userId,
            @PathVariable UUID cartItemId) {

        cartService.removeItem(getUser(userId), cartItemId);

        return "Item removed successfully";
    }

    @DeleteMapping("/clear/{userId}")
    public String clearCart(@PathVariable UUID userId) {

        cartService.clearCart(getUser(userId));

        return "Cart cleared successfully";
    }
}