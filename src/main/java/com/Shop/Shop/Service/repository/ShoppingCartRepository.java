package com.Shop.Shop.Service.repository;

import com.Shop.Shop.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings( "ALL" )
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    ShoppingCart findBySessionToken(String sessionToken);



}
