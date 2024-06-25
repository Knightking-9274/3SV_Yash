package com.nebulamart.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.nebulamart.backend.entities.Order;
import com.nebulamart.backend.exceptions.OrderException;
import com.nebulamart.backend.responses.ApiResponse;
import com.nebulamart.backend.services.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>>getAllOrdersHandlers(){
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(orders,HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> confirmedOrderHandler(@PathVariable Long orderId,
    @RequestHeader("Authorization")String jwt)throws OrderException{
        Order order = orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> shippedOrderHandler(@PathVariable Long orderId,
                @RequestHeader("Authorization")String jwt) throws OrderException{
                    Order order = orderService.shippedOrder(orderId);
                    return new ResponseEntity<Order>(order,HttpStatus.OK);
    }
    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order>deliverOrderHandler(@PathVariable Long OrderId,
                @RequestHeader("Authorization")String jwt)throws OrderException{
                    Order order = orderService.deliveredOrder(OrderId);
                    return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrderHandler(@PathVariable Long orderId,
                                @RequestHeader("Authorization")String jwt)throws OrderException{
        Order order =orderService.cancledOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse>deleteOrderHandler(@PathVariable Long orderId,
                                        @RequestHeader("Authorization")String jwt)throws OrderException{
            orderService.deleteOrder(orderId);
            ApiResponse res = new ApiResponse();
            res.setMessage("order deleted successfully!");
            res.setStatus(true);
            return new ResponseEntity<>(res,HttpStatus.OK);
            
    }
}    
