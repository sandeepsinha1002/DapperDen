package com.springboot.project.service;

import java.util.List;


import com.springboot.project.exception.OrderException;
import com.springboot.project.model.Address;
import com.springboot.project.model.Order;
import com.springboot.project.model.User;

public interface OrderService {
    public Order createOrder(User user,Address address);
    
    public Order findOrderbyId(Long OrderId) throws OrderException;
    
    public List<Order> usersOrderHistory(Long userId);
    
    public Order placedOrder(Long orderId) throws OrderException;
    
    public Order confirmedOrder(Long orderId)throws OrderException;
	
	public Order shippedOrder(Long orderId) throws OrderException;
	
	public Order deliveredOrder(Long orderId) throws OrderException;
	
	public Order cancledOrder(Long orderId) throws OrderException;
	
	public List<Order>getAllOrders();
	
	public void deleteOrder(Long orderId) throws OrderException;
	

    
}
