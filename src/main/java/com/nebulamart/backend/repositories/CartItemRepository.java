package com.nebulamart.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nebulamart.backend.entities.Cart;
import com.nebulamart.backend.entities.CartItems;
import com.nebulamart.backend.entities.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems,Long> {
    @Query("select ci from CartItems ci where ci.cart=:cart and ci.product=:product and ci.size=:size and ci.userId=:userId")
    public CartItems iscartItemExixts(@Param("cart")Cart cart,
                                    @Param("product")Product product,
                                    @Param("size")String size,
                                    @Param("userId")Long userId);
}
