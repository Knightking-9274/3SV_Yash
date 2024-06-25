package com.nebulamart.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nebulamart.backend.entities.Address;
import com.nebulamart.backend.entities.Order;
import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.OrderException;
import com.nebulamart.backend.exceptions.UserException;
import com.nebulamart.backend.services.OrderService;
import com.nebulamart.backend.services.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
                        @RequestHeader("Authorization")String jwt)throws UserException{
                            User user = userService.UserProfileByJwt(jwt);
                            Order order = orderService.createOrder(user, shippingAddress);
                            System.out.println("Order"+order);
                            return new ResponseEntity<Order>(order,HttpStatus.CREATED);
    }
    @GetMapping("/user")
    public ResponseEntity<List<Order>>usersOrderHistory(
        @RequestHeader("Authorization")String jwt)throws UserException
    {
        User user = userService.UserProfileByJwt(jwt);
        List<Order> orders = orderService.usersOrderHistory(user.getId());
        return new ResponseEntity<>(orders,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order>findOrderByid(@PathVariable("id")Long orderId,
                    @RequestHeader("Authorization")String jwt)throws UserException, OrderException{
        // User user = userService.UserProfileByJwt(jwt);
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }
}
