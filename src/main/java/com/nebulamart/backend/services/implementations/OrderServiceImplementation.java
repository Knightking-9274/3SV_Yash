package com.nebulamart.backend.services.implementations;

import java.util.List;
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
    private OrderItemService orderItemService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartService cartItemService;
    @Autowired
    private ProductService productService;

    @Override
    public Order createOrder(User user, Address shoppingAddress) {
        // shoppingAddress.setUser(user);
        // Address address= addressRepository.save(shoppingAddress);
        // user.getAddress().add(address);
        // userRepository.save(user);

        // Cart cart =cartService.findUserCart(user.getId());
        // List<OrderItems>  orderItems=new ArrayList<>();
        // for(CartItems items :cart.getCartItems()){
        //     OrderItems orderItem = new OrderItems();

        //     orderItem.setPrice(items.getPrice());
        //     orderItem.setProduct(items.getProduct());
        //     orderItem.setQuantity(items.getQuantity());
        //     return new Order();
        throw new UnsupportedOperationException("Unimplemented method 'usersOrderHistory'");
        }
    

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
         // TODO Auto-generated method stub
         throw new UnsupportedOperationException("Unimplemented method 'usersOrderHistory'");
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'usersOrderHistory'");
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placedOrder'");
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirmedOrder'");
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shippedOrder'");
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deliveredOrder'");
    }

    @Override
    public Order cancledOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancledOrder'");
    }

    @Override
    public List<Order> getAllOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllOrders'");
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }
    
}
