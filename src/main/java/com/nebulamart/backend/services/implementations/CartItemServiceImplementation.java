package com.nebulamart.backend.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.nebulamart.backend.entities.Cart;
import com.nebulamart.backend.entities.CartItems;
import com.nebulamart.backend.entities.Product;
import com.nebulamart.backend.entities.User;
import com.nebulamart.backend.exceptions.CartItemException;
import com.nebulamart.backend.exceptions.UserException;
import com.nebulamart.backend.repositories.CartItemRepository;
import com.nebulamart.backend.services.CartItemService;
import com.nebulamart.backend.services.UserService;

@Service
public class CartItemServiceImplementation implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    
    @Override
    public CartItems createCartItems(CartItems cartItems) {
        cartItems.setQuantity(1);
        cartItems.setPrice(cartItems.getProduct().getPrice()*cartItems.getQuantity());
        cartItems.setDiscountedPrice(cartItems.getProduct().getDiscountedPrice()*cartItems.getQuantity());
        CartItems createdCartItem = cartItemRepository.save(cartItems);
        return createdCartItem;
    }

    @Override
    public CartItems updateCartItems(Long userId, Long id, CartItems cartItem) throws CartItemException, UserException {
        CartItems items = findCartItemById(id);
        User user  = userService.findUserById(userId);
        if(user.getId().equals(userId)){
            items.setQuantity(cartItem.getQuantity());
            items.setPrice(items.getQuantity()*items.getProduct().getPrice());
            items.setDiscountedPrice(items.getProduct().getDiscountedPrice()*items.getQuantity());
        }
        return cartItemRepository.save(items);
    }

    @Override
    public CartItems isCartItemExists(Cart cart, Product product, String size, Long UserId) {
        CartItems cartItems = cartItemRepository.iscartItemExixts(cart, product, size, UserId);
        return cartItems;
    }

    @Override
    public void removeCartItems(Long userId, Long cartItemsId) throws CartItemException, UserException {
        CartItems cartItems = findCartItemById(cartItemsId);
        User user = userService.findUserById(cartItems.getUserId());
        User reqUser = userService.findUserById(userId);
        if(user.getId().equals(reqUser.getId())){
            cartItemRepository.deleteById(cartItemsId);
        }
        else{
            throw new UserException("You cannot remove other user's item!");
        }
    }

    @Override
    public CartItems findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItems> opt = cartItemRepository.findById(cartItemId);

        if(opt.isPresent()){
            return opt.get();
        }
        else{
            throw new CartItemException("Cart Item not found!");
        }

    }
    
}
