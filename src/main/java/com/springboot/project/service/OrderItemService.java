package com.springboot.project.service;

import org.springframework.stereotype.Service;

import com.springboot.project.model.OrderItem;

@Service
public interface OrderItemService {
    public OrderItem createOrderItem(OrderItem orderItem);
}
