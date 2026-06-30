package com.atul.jewelry.cart.mapper;

import com.atul.jewelry.cart.dto.response.CartItemResponse;
import com.atul.jewelry.cart.dto.response.CartResponse;
import com.atul.jewelry.cart.entity.Cart;
import com.atul.jewelry.cart.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {

    public CartItemResponse toCartItemResponse(CartItem item) {

        return CartItemResponse.builder()
                .cartItemId(item.getId().toString())
                .productId(item.getProduct().getId().toString())
                .productName(item.getProduct().getName())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .totalPrice(item.getTotalPrice())
                .build();
    }

    public CartResponse toCartResponse(Cart cart) {

        List<CartItemResponse> items = cart.getItems()
                .stream()
                .map(this::toCartItemResponse)
                .toList();

        return CartResponse.builder()
                .cartId(cart.getId().toString())
                .totalItems(items.size())
                .totalAmount(cart.getTotalAmount())
                .items(items)
                .build();
    }

}