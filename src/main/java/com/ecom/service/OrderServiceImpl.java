package com.ecom.service;

import java.util.List;
import java.util.Optional;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.model.Address;
import com.ecom.model.Cart;
import com.ecom.model.CartItem;
import com.ecom.model.Order;
import com.ecom.model.OrderItem;
import com.ecom.model.User;
import com.ecom.repository.AddressRepository;
import com.ecom.repository.OrderItemRepository;
import com.ecom.repository.OrderRepository;
import com.ecom.repository.UserRepository;
import com.ecom.request.CreateOrderRequest;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(CreateOrderRequest req, User user) throws Exception {
        Address shippedAddress = req.getDeliveryAddress();
        Address savAddress = addressRepository.save(shippedAddress);
        if (!user.getAddresses().contains(savAddress)) {
            user.getAddresses().add(savAddress);
            userRepository.save(user);
        }

        Order order = new Order();
        order.setOrderStatus("Pending");
        order.setDeliveryAddress(savAddress);
        order.setCustomer(user);

        Cart cart = cartService.findCartByUserId(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }
        order.setOrderItems(orderItems);
        Double totalPrice = cartService.calculateCartTotals(cart);
        order.setTotalAmount(totalPrice);

        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if (orderStatus != null) {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        } else {
            throw new Exception("Order status cannot be null");
        }

    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new Exception("Order not found with this id " + orderId);
        }
        return order.get();
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {
        List<Order> orders = orderRepository.findByCustomerId(userId);
        if (orders.isEmpty()) {
            throw new Exception("Order not found with this id " + userId);
        }
        return orders;
    }

    @Override
    public List<Order> getAllOrders(String orderStatus) throws Exception {

        List<Order> orders = orderRepository.findAll();

        if (!orders.isEmpty()) {
            orders = orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).toList();

        }
        return orders;
    }

}
