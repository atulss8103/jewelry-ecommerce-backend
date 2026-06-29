package com.atul.jewelry.product.service;

import com.atul.jewelry.product.dto.request.CreateProductRequest;
import com.atul.jewelry.product.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(String id);

}