package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.Product;
import com.learn.springdatajpa.model.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void saveProductCategory() {
        ProductCategory productCategory = new ProductCategory();

        productCategory.setCategoryName("books");
        productCategory.setCategoryDesc("book description");

        Product product1 = new Product();
        product1.setName("core java");
        product1.setDescription("learn core java");
        product1.setSku("book-001");
        product1.setPrice(new BigDecimal(185));
        product1.setActive(true);
        product1.setImageUrl("book-1.png");
        product1.setProductCategory(productCategory);

        Product product2 = new Product();
        product2.setName("python");
        product2.setDescription("learn core python");
        product2.setSku("book-002");
        product2.setPrice(new BigDecimal(355));
        product2.setActive(true);
        product2.setImageUrl("book-2.png");
        product2.setProductCategory(productCategory);


        productCategory.setProducts(new ArrayList<>());
        productCategory.getProducts().add(product1);
        productCategory.getProducts().add(product2);

        productCategoryRepository.save(productCategory);
    }

    @Test
    @Transactional
    public void getProductCategory() {
        ProductCategory productCategory = productCategoryRepository.findById(1L).get();

        for (Product product : productCategory.getProducts()) {
            System.out.println(product.getName());
        }

    }

}