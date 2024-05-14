package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.Address;
import com.learn.springdatajpa.model.Order;
import com.learn.springdatajpa.model.OrderItem;
import com.learn.springdatajpa.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class OneToManyMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveOrder() {
        Order order = new Order();
        order.setOrderTrackingNumber("10001");
        order.setTotalQty(0);
        order.setTotalPrice(new BigDecimal(0));
        order.setStatus("in-progress");

        Address address = new Address();
        address.setStreet("Kovilpalayam");
        address.setCity("Cbe");
        address.setState("TN");
        address.setCountry("india");
        address.setZipCode(641107L);

        order.setBillingAddress(address);
        address.setOrder(order);

        //Add order items1
        OrderItem orderItem1 = new OrderItem();
        //Get product
        Product product1 = productRepository.findById(1L).get();
        orderItem1.setProduct(product1);
        orderItem1.setQty(2);
        orderItem1.setPrice(product1.getPrice().multiply(new BigDecimal(orderItem1.getQty())));
        orderItem1.setImgUrl(product1.getImageUrl());
        orderItem1.setOrder(order);

        //Add order items2
        OrderItem orderItem2 = new OrderItem();
        //Get product
        Product product2 = productRepository.findById(2L).get();
        orderItem2.setProduct(product2);
        orderItem2.setQty(5);
        orderItem2.setPrice(product2.getPrice().multiply(new BigDecimal(orderItem2.getQty())));
        orderItem2.setImgUrl(product2.getImageUrl());
        orderItem2.setOrder(order);


        Set<OrderItem> orderItemSet = new HashSet<>();
        orderItemSet.add(orderItem1);
        orderItemSet.add(orderItem2);
        order.setOrderItems(orderItemSet);

        order.setTotalOrderPriceAndQty();


        Order order1 = orderRepository.save(order);


        System.out.println(order1);
    }

    @Test
    public void getOrder() {
        Order order = orderRepository.findById(1L).get();

        System.out.println("Order : " + order);
        for (OrderItem orderItem : order.getOrderItems()) {
            System.out.println(orderItem.getProduct().getName());
        }
    }


    @Test
    public void deleteOrder() {
        orderRepository.deleteById(1L);
    }

}
