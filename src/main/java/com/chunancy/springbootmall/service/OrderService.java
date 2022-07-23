package com.chunancy.springbootmall.service;

import com.chunancy.springbootmall.dto.CreateOrderRequest;
import com.chunancy.springbootmall.model.Order;

public interface OrderService {
    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
