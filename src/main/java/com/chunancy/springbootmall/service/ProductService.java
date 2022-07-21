package com.chunancy.springbootmall.service;

import com.chunancy.springbootmall.dto.ProductRequest;
import com.chunancy.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
