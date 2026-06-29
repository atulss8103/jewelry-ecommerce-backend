package com.atul.jewelry.cart.service;

import com.atul.jewelry.cart.dto.request.AddToCartRequest;
import com.atul.jewelry.cart.dto.request.UpdateQuantityRequest;
import com.atul.jewelry.cart.dto.response.CartResponse;

import java.util.UUID;

public interface CartService {

    CartResponse addToCart(UUID userId, AddToCartRequest request);

    CartResponse getCart(String userEmail);

    CartResponse updateQuantity(String userEmail, UpdateQuantityRequest request);

    void removeItem(String userEmail, String cartItemId);

    void clearCart(String userEmail);

}