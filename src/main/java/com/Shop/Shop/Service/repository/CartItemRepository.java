package com.Shop.Shop.Service.repository;

import com.Shop.Shop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
