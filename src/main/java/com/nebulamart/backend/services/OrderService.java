package com.nebulamart.backend.services;

import java.util.List;

import com.nebulamart.backend.entities.Address;
import com.nebulamart.backend.entities.Order;
import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.OrderException;

public interface OrderService {
    
    public Order createOrder(User user, Address shoppingAddress);
    public Order findOrderById(Long orderId) throws OrderException;
    public List<Order> usersOrderHistory(Long userId);
    public Order placedOrder(Long orderId)throws OrderException;
    public Order confirmedOrder(Long orderId)throws OrderException;
    public Order shippedOrder(Long orderId)throws OrderException;
    public Order deliveredOrder(Long orderId)throws OrderException;
    public Order cancledOrder(Long orderId)throws OrderException; 
    public List<Order> getAllOrders();
    public void deleteOrder(Long orderId)throws OrderException;
}

