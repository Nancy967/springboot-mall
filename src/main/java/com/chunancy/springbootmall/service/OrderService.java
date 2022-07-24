package com.chunancy.springbootmall.service;

import com.chunancy.springbootmall.dto.CreateOrderRequest;
import com.chunancy.springbootmall.dto.OrderQueryParams;
import com.chunancy.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
