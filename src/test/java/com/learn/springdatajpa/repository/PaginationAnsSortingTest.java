package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PaginationAnsSortingTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void productPagination() {
        int pageNo = 0;
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);
        while (productPage.hasContent()) {
            List<Product> productList = productPage.getContent();
            productList.forEach(System.out::println);
            pageNo++;
            productPage = productRepository.findAll(PageRequest.of(pageNo, pageSize));
            System.out.println("======= NEXT PAGE=======");
        }
        System.out.println("Total pages : " + productPage.getTotalPages());
        System.out.println("Total Elements : " + productPage.getTotalElements());
        System.out.println("Number of elements in pages : " + productPage.getNumberOfElements());
        System.out.println("size : " + productPage.getSize());
        System.out.println("is Last page : " + productPage.isLast());
        System.out.println("is First page : " + productPage.isFirst());
        System.out.println(productPage.hasNext());
    }

    @Test
    public void productSortingByPrice() {
        String sortDirection = "desc";
        String sortBy = "price";
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        List<Product> productList = productRepository.findAll(sort);
        productList.forEach(System.out::println);
    }

    @Test
    public void productSortingByMultipleFields() {
        String sortDirection = "desc";
        String sortByPrice = "price";
        String sortByName = "name";
        Sort sortByPriceEntity = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortByPrice).ascending() : Sort.by(sortByPrice).descending();
        Sort sortByNameEntity = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortByName).ascending() : Sort.by(sortByName).descending();
        Sort groupBySort = sortByPriceEntity.and(sortByNameEntity);
        List<Product> productList = productRepository.findAll(groupBySort);
        productList.forEach(System.out::println);
    }

    @Test
    public void productPaginationAndSortingTogether() {
        int pageNumber = 0;
        int pageSize = 5;
        String sortDirection = "desc";
        String sortByPrice = "price";
        Sort sortByPriceEntity = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortByPrice).ascending() : Sort.by(sortByPrice).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByPriceEntity);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> productList = productPage.getContent();
        productList.forEach(System.out::println);
    }


}
