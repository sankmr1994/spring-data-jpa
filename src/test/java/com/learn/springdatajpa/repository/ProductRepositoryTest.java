package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;


@SpringBootTest
class ProductRepositoryTest {


    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveProduct() {
        Product product = new Product();
        product.setName("Product-1");
        product.setDescription("Product-1 description detail");
        product.setSku("sku-001");
        product.setPrice(new BigDecimal(101));
        product.setActive(true);
        product.setImageUrl("product-1.png");

        Product product1 = productRepository.save(product);

        System.out.println(product1);

    }

    @Test
    public void updateProduct() {
        long id = 1L;
        Product product = productRepository.findById(id).get();
        product.setDescription("Product-1 description detail updated");
        Product product1 = productRepository.save(product);

        System.out.println(product1);

    }

    @Test
    public void findProductById() {
        long id = 1L;
        Product product = productRepository.findById(id).get();
        System.out.println(product);
    }

    @Test
    public void saveAllProducts() {

        Product product1 = new Product();
        product1.setName("Product-1");
        product1.setDescription("Product-1 description detail");
        product1.setSku("sku-001");
        product1.setPrice(new BigDecimal(200));
        product1.setActive(true);
        product1.setImageUrl("product-1.png");

        Product product2 = new Product();
        product2.setName("Product-2");
        product2.setDescription("Product-2 description detail");
        product2.setSku("sku-002");
        product2.setPrice(new BigDecimal(525));
        product2.setActive(true);
        product2.setImageUrl("product-2.png");

        productRepository.saveAll(List.of(product1, product2));

    }

    @Test
    public void findAllProducts() {
        List<Product> products = productRepository.findAll();
        products.forEach(System.out::println);
    }

    @Test
    public void deleteProductById() {
        productRepository.deleteById(1L);
    }

    @Test
    public void deleteProductByEntity() {
        long id = 1L;
        Product product = productRepository.findById(id).get();
        // Low in performance compare to deleteById()
        productRepository.delete(product);
    }

    @Test
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Test
    public void deleteMultipleProducts() {
        long id1 = 1L;
        Product product1 = productRepository.findById(id1).get();
        long id2 = 2L;
        Product product2 = productRepository.findById(id2).get();
        productRepository.deleteAll(List.of(product1, product2));
    }

    @Test
    public void countOfProducts() {
        long count = productRepository.count();
        System.out.println("Total number of products : " + count);
    }

    @Test
    public void checkProductExistsOrNot() {
        boolean isExists = productRepository.existsById(1L);
        System.out.println("Product exists : " + isExists);
    }

}