package com.nebulamart.backend.services;

import com.nebulamart.backend.entities.Cart;
import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.ProductException;
import com.nebulamart.backend.requests.AddItemRequests;

public interface CartService {
    public Cart createCart(User user);
    public String addCartItem(Long userId,AddItemRequests req)throws ProductException;
    public Cart findUserCart(Long userID);
}
