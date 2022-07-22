package com.chunancy.springbootmall.dao;

import com.chunancy.springbootmall.dto.ProductQueryParams;
import com.chunancy.springbootmall.dto.ProductRequest;
import com.chunancy.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer createproduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
