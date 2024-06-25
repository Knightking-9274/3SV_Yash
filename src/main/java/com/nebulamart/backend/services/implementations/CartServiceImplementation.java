package com.nebulamart.backend.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebulamart.backend.entities.Cart;
import com.nebulamart.backend.entities.CartItems;
import com.nebulamart.backend.entities.Product;
import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.ProductException;
import com.nebulamart.backend.repositories.CartRepository;
import com.nebulamart.backend.requests.AddItemRequests;
import com.nebulamart.backend.services.CartItemService;
import com.nebulamart.backend.services.CartService;
import com.nebulamart.backend.services.ProductService;

@Service
public class CartServiceImplementation implements CartService {
    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;

    public CartServiceImplementation(CartRepository cartRepository,CartItemService cartItemService, ProductService productService){
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Cart createCart(User user) {
      Cart cart = new Cart();
      cart.setUser(user);
      return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequests req) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product  = productService.findProductById(req.getProductId());
        CartItems isPresent = cartItemService.isCartItemExists(cart, product, req.getSize(), userId);
        if(isPresent==null){
            CartItems cartItems = new CartItems();
            cartItems.setProduct(product);
            cartItems.setCart(cart);
            cartItems.setQuantity(req.getQuantity());
            cartItems.setUserId(userId);
            int price = req.getQuantity()*product.getDiscountedPrice();
            cartItems.setPrice(price);
            cartItems.setSize(req.getSize());
            CartItems createdItem = cartItemService.createCartItems(cartItems);
            cart.getCartItems().add(createdItem); 
        }
        return "Item added to cart";
    }

    @Override
    public Cart findUserCart(Long userID) {
        Cart cart = cartRepository.findByUserId(userID);
        int totalPrice=0;
        int totlaDiscountedPrice=0;
        int totalItem=0;
        for(CartItems cartItems: cart.getCartItems()){
            totalPrice+=cartItems.getPrice();
            totlaDiscountedPrice+=cartItems.getDiscountedPrice();
            totalItem+=cartItems.getQuantity();
        }
        cart.setTotalPrice(totalPrice);
        cart.setTotalDiscountedPrice(totlaDiscountedPrice);
        cart.setTotalItem(totalItem);
        cart.setDiscount(totalPrice-totlaDiscountedPrice);
        return cartRepository.save(cart);
    }
    
    
}
