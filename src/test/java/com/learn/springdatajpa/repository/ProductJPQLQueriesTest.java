package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductJPQLQueriesTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findByProductNameOrDescUsingIndexPosition(){
        Product product = productRepository.findByNameOrDescUsingIndexPosition("Product-3","Product-3 description detail");
        System.out.println("Product : " + product);
    }

    @Test
    public void findByProductNameOrDescUsingNamedParam(){
        Product product = productRepository.findByNameOrDescUsingNamedParam("Product-2","Product-2 description detail");
        System.out.println("Product : " + product);
    }

    @Test
    public void findByProductNameOrDescUsingIndexPositionNativeSQLQuery(){
        Product product = productRepository.findByNameOrDescUsingIndexPositionNativeSQLQuery("Product-3","Product-3 description detail");
        System.out.println("Product : " + product);
    }

    @Test
    public void findByProductNameOrDescUsingNamedParamNativeSQLQuery(){
        Product product = productRepository.findByNameOrDescUsingNamedParamNativeSQLQuery("Product-2","Product-2 description detail");
        System.out.println("Product : " + product);
    }
}
