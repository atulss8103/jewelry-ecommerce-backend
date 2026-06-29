package com.atul.jewelry.cart.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class CartResponse {

    private String cartId;

    private Integer totalItems;

    private BigDecimal totalAmount;

    private List<CartItemResponse> items;

}