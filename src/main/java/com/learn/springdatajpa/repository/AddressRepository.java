package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
