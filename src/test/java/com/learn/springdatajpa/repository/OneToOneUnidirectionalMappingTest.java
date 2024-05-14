package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.Address;
import com.learn.springdatajpa.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneUnidirectionalMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void saveOrder() {
        Order order = new Order();
        order.setOrderTrackingNumber("10001");
        order.setTotalQty(5);
        order.setTotalPrice(new BigDecimal(550));
        order.setStatus("in-progress");

        Address address = new Address();
        address.setStreet("Kovilpalayam");
        address.setCity("Cbe");
        address.setState("TN");
        address.setCountry("india");
        address.setZipCode(641107L);

        order.setBillingAddress(address);

        Order order1 = orderRepository.save(order);
        System.out.println(order1);
    }

    @Test
    public void getOrder() {
        Order order = orderRepository.findById(2L).get();
        System.out.println(order);
    }

    @Test
    public void updateOrder() {
        long id = 1L;
        Order order = orderRepository.findById(id).get();
        order.setStatus("completed");
        order.getBillingAddress().setZipCode(641106L);

        Order order1 = orderRepository.save(order);
        System.out.println(order1);
    }

    @Test
    public void deleteOrder() {
        orderRepository.deleteById(1L);
    }


}
