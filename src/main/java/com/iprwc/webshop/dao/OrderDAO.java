package com.iprwc.webshop.dao;

import com.iprwc.webshop.model.Order;
import com.iprwc.webshop.repositories.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderDAO {

    private final OrderRepository orderRepository;

    public OrderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order store(Order order) {
        return orderRepository.save(order);
    }
}