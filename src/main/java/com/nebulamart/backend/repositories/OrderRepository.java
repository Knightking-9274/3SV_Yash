package com.nebulamart.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.nebulamart.backend.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.user.id =:userId and(o.orderStatus='PLACED' or o.orderStatus='CONFIRMED' or o.orderStatus='SHIPPED' or o.orderStatus='DELIVERED')")
    public List<Order>getUsersOrders(@Param("userId")Long userId);
}
