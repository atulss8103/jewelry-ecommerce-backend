package com.atul.jewelry.cart.service;

import com.atul.jewelry.auth.entity.User;
import com.atul.jewelry.cart.dto.request.AddToCartRequest;
import com.atul.jewelry.cart.dto.request.UpdateQuantityRequest;
import com.atul.jewelry.cart.dto.response.CartResponse;

import java.util.UUID;

public interface CartService {

    CartResponse addToCart(User user, AddToCartRequest request);

    CartResponse getCart(User user);

    CartResponse updateQuantity(User user, UpdateQuantityRequest request);

    void removeItem(User user, UUID cartItemId);

    void clearCart(User user);

}