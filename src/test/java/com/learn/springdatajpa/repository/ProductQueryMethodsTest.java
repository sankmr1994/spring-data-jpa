package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProductQueryMethodsTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findByProductName() {
        Product product = productRepository.findByName("Product-2");
        System.out.println("Product : " + product);
    }

    @Test
    public void findByProductId() {
        Product product = productRepository.findById(3L).get();
        System.out.println("Product : " + product);
    }

    @Test
    public void findByProductNameAndDescription() {
        Product product = productRepository.findByNameAndDescription("Product-2", "Product-2 description detail");
        System.out.println("Product : " + product);
    }

    @Test
    public void findByDistinctProductByName() {
        Product product = productRepository.findDistinctByName("Product-2");
        System.out.println("Product : " + product);
    }

    @Test
    public void findByProductGreaterThanPrice() {
        List<Product> product = productRepository.findByPriceGreaterThan(new BigDecimal(120));
        System.out.println("Product : " + product);
    }

    @Test
    public void findByProductLessThanPrice() {
        List<Product> product = productRepository.findByPriceLessThan(new BigDecimal(120));
        System.out.println("Product : " + product);
    }

    @Test
    public void findByProductContaining() {
        List<Product> product = productRepository.findByNameContaining("product");
        System.out.println("Product : " + product);
    }

    @Test
    public void findByProductLike() {
        List<Product> product = productRepository.findByNameLike("product");
        System.out.println("Product : " + product);
    }

    @Test
    public void findByProductBetweenPrice() {
        List<Product> product = productRepository.findByPriceBetween(new BigDecimal(105), new BigDecimal(600));
        System.out.println("Product : " + product);
    }

    @Test
    public void findByProductBetweenDate() {
        LocalDateTime startDate = LocalDateTime.of(2024, 05, 13, 9, 40, 06);
        LocalDateTime endDate = LocalDateTime.of(2024, 05, 13, 9, 50, 14);
        List<Product> product = productRepository.findByCreatedAtBetween(startDate, endDate);
        System.out.println("Product : " + product);
    }

    @Test
    public void findByProductIn() {
        List<String> productNames = List.of("Product-2", "Product-1");
        List<Product> product = productRepository.findByNameIn(productNames);
        System.out.println("Product : " + product);
    }

    @Test
    public void findByTop2Product() {
        List<Product> product = productRepository.findTop2ByOrderByNameAsc();
        System.out.println("Product : " + product);
    }
}
