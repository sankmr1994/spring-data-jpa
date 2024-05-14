package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.Address;
import com.learn.springdatajpa.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneBidirectionalMappingTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void saveAddress() {
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
        address.setOrder(order);

        Address address1 = addressRepository.save(address);
        System.out.println(address1);
    }

    @Test
    public void getAddress() {
        Address address = addressRepository.findById(1L).get();
        System.out.println(address);
    }

    @Test
    public void updateAddress() {
        long id = 1L;
        Address address = addressRepository.findById(id).get();
        address.setCity("Bangalore");
        address.getOrder().setStatus("Completed");

        Address address1 = addressRepository.save(address);
        System.out.println(address1);
    }

    @Test
    public void deleteAddress() {
        addressRepository.deleteById(1L);
    }


}
