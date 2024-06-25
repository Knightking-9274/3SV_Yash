package com.nebulamart.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nebulamart.backend.entities.OrderItems;

public interface OrderItemRepository extends JpaRepository<OrderItems,Long>{
    
}
