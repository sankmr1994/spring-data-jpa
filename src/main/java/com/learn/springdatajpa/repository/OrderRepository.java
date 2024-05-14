package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderTrackingNumber(String orderNumber);
}
