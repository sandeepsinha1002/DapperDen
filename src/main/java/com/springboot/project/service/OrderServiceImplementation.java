package com.springboot.project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.project.exception.OrderException;
import com.springboot.project.model.Address;
import com.springboot.project.model.Cart;
import com.springboot.project.model.CartItem;
import com.springboot.project.model.Order;
import com.springboot.project.model.OrderItem;
import com.springboot.project.model.User;
import com.springboot.project.repository.AddressRepository;
import com.springboot.project.repository.OrderItemRepository;
import com.springboot.project.repository.OrderRepository;
import com.springboot.project.repository.UserRepository;
import com.springboot.project.user.domain.OrderStatus;
import com.springboot.project.user.domain.PaymentStatus;

@Service
public class OrderServiceImplementation implements OrderService {

    private OrderRepository orderRepository;
    private CartService cartService;
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private OrderItemService orderItemService;
    private OrderItemRepository orderItemRepository;

    


    public OrderServiceImplementation(OrderRepository orderRepository, CartService cartService,
            AddressRepository addressRepository, UserRepository userRepository, OrderItemService orderItemService,
            OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.orderItemService = orderItemService;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Order createOrder(User user, Address shippingaddress) {
        shippingaddress.setUser(user);
        Address address = addressRepository.save(shippingaddress);
        user.getAddress().add(address);
        userRepository.save(user);
        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems=new ArrayList<>();
        
        for(CartItem item:cart.getCartItems())
        {
            OrderItem orderItem = new OrderItem();

            orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());

            OrderItem createdOrderItem = orderItemRepository.save(orderItem);

            orderItems.add(createdOrderItem);
        }
        Order createdOrder = new Order();
        createdOrder.setUser(user);
        createdOrder.setOrderItems(orderItems);
        createdOrder.setTotalItem(cart.getTotalItem());
        createdOrder.setTotalPrice(cart.getTotalPrice());
        createdOrder.setShippingAddress(address);
        createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus(OrderStatus.PENDING);
		createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);
		createdOrder.setCreatedAt(LocalDateTime.now());
        Order savedOrder=orderRepository.save(createdOrder);
		
		for(OrderItem item:orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		
		return savedOrder;

        }

    @Override
    public Order findOrderbyId(Long OrderId) throws OrderException {
      
        Optional<Order> opt =orderRepository.findById(OrderId);
        System.out.println("optional" + opt);
        if(opt.isPresent())
        {
            return opt.get();
        }
        throw new OrderException("Order does not exist with id  " + OrderId);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) 
    {
        System.out.println("user : "+userId);
        List<Order> orders= orderRepository.getUsersOrders(userId);
        System.out.println("orders  "+ orders);
        return orders;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
          Order order = findOrderbyId(orderId);
        order.setOrderStatus(OrderStatus.PLACED);
		order.getPaymentDetails().setStatus(PaymentStatus.COMPLETED);
        return order;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
          Order order = findOrderbyId(orderId);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        Order order = findOrderbyId(orderId);
        order.setOrderStatus(OrderStatus.SHIPPED);
        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        Order order = findOrderbyId(orderId);
        order.setOrderStatus(OrderStatus.DELIVERED);
        return orderRepository.save(order);
    }

    @Override
    public Order canceledOrder(Long orderId) throws OrderException {
        Order order = findOrderbyId(orderId);
        order.setOrderStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Order order=findOrderbyId(orderId);
		orderRepository.deleteById(orderId);
    }
    
}
