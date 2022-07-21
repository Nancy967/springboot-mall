package com.chunancy.springbootmall.dao;

import com.chunancy.springbootmall.dto.ProductRequest;
import com.chunancy.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
    Integer createproduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}
