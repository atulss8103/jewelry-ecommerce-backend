package com.atul.jewelry.product.controller;

import com.atul.jewelry.product.dto.request.CreateProductRequest;
import com.atul.jewelry.product.dto.response.ProductResponse;
import com.atul.jewelry.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody CreateProductRequest request){

        return productService.createProduct(request);

    }

    @GetMapping
    public List<ProductResponse> getAllProducts(){

        return productService.getAllProducts();

    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable String id){

        return productService.getProductById(id);

    }

}