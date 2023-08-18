package com.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    
}
