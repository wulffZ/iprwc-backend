package com.iprwc.webshop.repositories;

import com.iprwc.webshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
