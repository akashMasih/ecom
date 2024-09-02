package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
