package com.chunancy.springbootmall.dao;

import com.chunancy.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}
