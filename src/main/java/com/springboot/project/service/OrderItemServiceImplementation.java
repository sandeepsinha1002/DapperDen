package com.springboot.project.service;

import com.springboot.project.model.OrderItem;
import com.springboot.project.repository.OrderItemRepository;

public class OrderItemServiceImplementation implements OrderItemService {

    private OrderItemRepository orderItemRepository;
     public OrderItemServiceImplementation(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    
    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {

        return orderItemRepository.save(orderItem);
    }

   
}
