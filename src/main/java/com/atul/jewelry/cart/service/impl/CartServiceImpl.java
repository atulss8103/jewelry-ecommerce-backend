package com.atul.jewelry.cart.service.impl;

import com.atul.jewelry.auth.entity.User;
import com.atul.jewelry.auth.repository.UserRepository;
import com.atul.jewelry.cart.dto.request.AddToCartRequest;
import com.atul.jewelry.cart.dto.request.UpdateQuantityRequest;
import com.atul.jewelry.cart.dto.response.CartResponse;
import com.atul.jewelry.cart.entity.Cart;
import com.atul.jewelry.cart.entity.CartItem;
import com.atul.jewelry.cart.mapper.CartMapper;
import com.atul.jewelry.cart.repository.CartItemRepository;
import com.atul.jewelry.cart.repository.CartRepository;
import com.atul.jewelry.cart.service.CartService;
import com.atul.jewelry.exception.BadRequestException;
import com.atul.jewelry.exception.ResourceNotFoundException;
import com.atul.jewelry.product.entity.Product;
import com.atul.jewelry.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;

    @Override
    public CartResponse addToCart(User user, AddToCartRequest request) {

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        Product product = productRepository.findById(UUID.fromString(request.getProductId()))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (request.getQuantity() > product.getStock()) {
            throw new BadRequestException("Requested quantity exceeds available stock");
        }

        CartItem cartItem = cartItemRepository
                .findByCartAndProduct(cart, product)
                .orElse(null);

        if (cartItem != null) {

            int newQuantity = cartItem.getQuantity() + request.getQuantity();

            if (newQuantity > product.getStock()) {
                throw new BadRequestException("Requested quantity exceeds available stock");
            }

            cartItem.setQuantity(newQuantity);
            cartItem.setUnitPrice(product.getPrice());
            cartItem.setTotalPrice(
                    product.getPrice().multiply(BigDecimal.valueOf(newQuantity))
            );

        } else {

            cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .unitPrice(product.getPrice())
                    .totalPrice(
                            product.getPrice().multiply(
                                    BigDecimal.valueOf(request.getQuantity())
                            )
                    )
                    .build();

            cart.getItems().add(cartItem);
        }

        cartItemRepository.save(cartItem);

        calculateCartTotal(cart);

        cartRepository.save(cart);

        return cartMapper.toCartResponse(cart);
    }

    @Override
    public CartResponse getCart(User user) {

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        calculateCartTotal(cart);

        return cartMapper.toCartResponse(cart);
    }

    @Override
    public CartResponse updateQuantity(User user, UpdateQuantityRequest request) {

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        CartItem cartItem = cartItemRepository.findById(
                        UUID.fromString(request.getCartItemId()))
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new BadRequestException("Cart item does not belong to this user");
        }

        if (request.getQuantity() > cartItem.getProduct().getStock()) {
            throw new BadRequestException("Quantity exceeds available stock");
        }

        cartItem.setQuantity(request.getQuantity());

        cartItem.setTotalPrice(
                cartItem.getUnitPrice()
                        .multiply(BigDecimal.valueOf(request.getQuantity()))
        );

        cartItemRepository.save(cartItem);

        calculateCartTotal(cart);

        cartRepository.save(cart);

        return cartMapper.toCartResponse(cart);
    }

    @Override
    public void removeItem(User user, UUID cartItemId) {

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new BadRequestException("Cart item does not belong to this user");
        }

        cart.getItems().remove(cartItem);

        cartItemRepository.delete(cartItem);

        calculateCartTotal(cart);

        cartRepository.save(cart);
    }

    @Override
    public void clearCart(User user) {

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cartItemRepository.deleteAll(cart.getItems());

        cart.getItems().clear();

        cart.setTotalAmount(BigDecimal.ZERO);

        cartRepository.save(cart);
    }

    private void calculateCartTotal(Cart cart) {

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem item : cart.getItems()) {
            total = total.add(item.getTotalPrice());
        }

        cart.setTotalAmount(total);
    }

}