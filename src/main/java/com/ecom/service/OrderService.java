package com.ecom.service;

import com.ecom.model.Order;
import com.ecom.model.User;
import com.ecom.request.CreateOrderRequest;

import java.util.List;

public interface OrderService {

    public Order createOrder(CreateOrderRequest req, User user) throws Exception;

    public Order updateOrder(Long orderId, String orderStatus) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUsersOrder(Long userId) throws Exception;

    public List<Order> getAllOrders(String orderStatus) throws Exception;

}
