package com.nebulamart.backend.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebulamart.backend.entities.OrderItems;
import com.nebulamart.backend.repositories.OrderItemRepository;
import com.nebulamart.backend.services.OrderItemService;

@Service
public class OrderItemServiceImplementation implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public OrderItems creaOrderItems(OrderItems orderItems) {
      return orderItemRepository.save(orderItems);
    }
    
}
