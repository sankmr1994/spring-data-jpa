package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class ProductNamedQueryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findBySku() {
        Product product = productRepository.findBySku("sku-003");
        System.out.println("Product : " + product);
    }

    @Test
    public void findByPrice() {
        List<Product> product = productRepository.findByPrice(new BigDecimal("101.00"));
        System.out.println("Product : " + product);
    }

    @Test
    public void findBySkuNativeSQLQuery() {
        Product product = productRepository.findBySku("sku-003");
        System.out.println("Product : " + product);
    }

    @Test
    public void findByPriceNativeSQLQuery() {
        List<Product> product = productRepository.findByPrice(new BigDecimal("101.00"));
        System.out.println("Product : " + product);
    }

    @Test
    public void findAllProductNameByDescOrder() {
        List<Product> product = productRepository.findAllProductNameByDescOrder();
        System.out.println("Product : " + product);
    }

    @Test
    public void findHighestPriceProducts() {
        List<Product> product = productRepository.findHighestPriceProducts();
        System.out.println("Product : " + product);
    }

}
