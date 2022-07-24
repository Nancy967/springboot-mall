package com.chunancy.springbootmall.service.impl;

import com.chunancy.springbootmall.dao.OrderDao;
import com.chunancy.springbootmall.dao.ProductDao;
import com.chunancy.springbootmall.dao.UserDao;
import com.chunancy.springbootmall.dto.BuyItem;
import com.chunancy.springbootmall.dto.CreateOrderRequest;
import com.chunancy.springbootmall.dto.OrderQueryParams;
import com.chunancy.springbootmall.model.Order;
import com.chunancy.springbootmall.model.OrderItem;
import com.chunancy.springbootmall.model.Product;
import com.chunancy.springbootmall.model.User;
import com.chunancy.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);

        for (Order order : orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(order.getOrderId());

            order.setOrderItemList(orderItemList);
        }

        return orderList;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        User user = userDao.getUserById(userId);

        if (user == null){
            log.warn("該userId {} 已被註冊", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem: createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            if (product == null){
                log.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            if (product.getStock() < buyItem.getQuantity()){
                log.warn("商品 {} 庫存不足:庫存數量({})，欲購買數量({})",
                        buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            productDao.updateStock(product.getProductId(), product.getStock()- buyItem.getQuantity());

            int amount = product.getPrice() * buyItem.getQuantity();
            totalAmount = totalAmount + amount;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
