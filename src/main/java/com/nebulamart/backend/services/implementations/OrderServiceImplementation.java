package com.nebulamart.backend.services.implementations;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nebulamart.backend.entities.Address;
import com.nebulamart.backend.entities.Cart;
import com.nebulamart.backend.entities.CartItems;
import com.nebulamart.backend.entities.Order;
import com.nebulamart.backend.entities.OrderItems;
import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.OrderException;
import com.nebulamart.backend.repositories.AddressRepository;
import com.nebulamart.backend.repositories.CartRepository;
import com.nebulamart.backend.repositories.OrderItemRepository;
import com.nebulamart.backend.repositories.OrderRepository;
import com.nebulamart.backend.repositories.UserRepository;
import com.nebulamart.backend.services.CartService;
import com.nebulamart.backend.services.OrderItemService;
import com.nebulamart.backend.services.OrderService;
import com.nebulamart.backend.services.ProductService;

@Service
public class OrderServiceImplementation implements OrderService{
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public Order createOrder(User user, Address shoppingAddress) {
        shoppingAddress.setUser(user);
        Address address= addressRepository.save(shoppingAddress);
        user.getAddress().add(address);
        userRepository.save(user);

        Cart cart =cartService.findUserCart(user.getId());
        List<OrderItems> orderItemsList=new ArrayList<>();
        for(CartItems items :cart.getCartItems()){
            OrderItems orderItems = new OrderItems();

            orderItems.setPrice(items.getPrice());
            orderItems.setProduct(items.getProduct());
            orderItems.setQuantity(items.getQuantity());
            orderItems.setSize(items.getSize());
            orderItems.setUserID(items.getUserId());
            orderItems.setDiscountedPrice(items.getDiscountedPrice());
            OrderItems createdOrderItems = orderItemRepository.save(orderItems);
            orderItemsList.add(createdOrderItems);

        }
            Order createdOrder = new Order();
            createdOrder.setUser(user);
            createdOrder.setOrderItems(orderItemsList);
            createdOrder.setTotalPrice(cart.getTotalPrice());
            createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
            createdOrder.setDiscount(cart.getDiscount());
            createdOrder.setTotalItem(cart.getTotalItem());
            createdOrder.setShippingAddress(address);
            createdOrder.setOrderDate(LocalDateTime.now());
            createdOrder.setOrderStatus("Pending");
            createdOrder.getPaymentDetails().setPaymentStatus("Pending");
            createdOrder.setCreatedAt(LocalDateTime.now());

            Order savedOrder = orderRepository.save(createdOrder);

            for(OrderItems orderItem:orderItemsList) {
                    orderItem.setOrder(savedOrder);
                    orderItemRepository.save(orderItem);
            }   

        return savedOrder;
        }
    

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
         Optional<Order> opt = orderRepository.findById(orderId);
         if(opt.isPresent()){
            return opt.get();
         }
         throw new OrderException("Order does not exitst with id "+orderId);

    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        List<Order> orders = orderRepository.getUsersOrders(userId);
        return orders;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("PLACED");
        order.getPaymentDetails().setPaymentStatus("Completed");
        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");
        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("SHIPPED");
        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("DELIVERED");
        return orderRepository.save(order);
    }

    @Override
    public Order cancledOrder(Long orderId) throws OrderException {
        Order order = findOrderById(orderId);
        order.setOrderStatus("CANCELLED");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
         orderRepository.deleteById(orderId);
    }
    
}
