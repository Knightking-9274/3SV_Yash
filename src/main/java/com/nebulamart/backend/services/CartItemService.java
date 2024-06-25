package com.nebulamart.backend.services;
import com.nebulamart.backend.exceptions.CartItemException;
import com.nebulamart.backend.entities.Cart;
import com.nebulamart.backend.entities.CartItems;
import com.nebulamart.backend.entities.Product;
import com.nebulamart.backend.exceptions.UserException;

public interface CartItemService {
    public CartItems createCartItems(CartItems cartItems);
    public CartItems updateCartItems(Long userId, Long id, CartItems cartItem)throws CartItemException, UserException;
    public CartItems isCartItemExists(Cart cart, Product product, String size, Long UserId);
    public void removeCartItems(Long userId,Long cartItemsId)throws CartItemException,UserException;
    public CartItems findCartItemById(Long cartItemId) throws CartItemException;
    
}
