package com.learn.springdatajpa.repository;

import com.learn.springdatajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    Optional<Product> findById(Long id);

    Product findByNameAndDescription(String name, String desc);

    Product findDistinctByName(String name);

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceLessThan(BigDecimal price);

    List<Product> findByNameContaining(String value);

    List<Product> findByNameLike(String value);

    List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

    List<Product> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Product> findByNameIn(List<String> names);

    List<Product> findTop2ByOrderByNameAsc();


    @Query("SELECT p FROM Product p WHERE p.name = ?1 OR p.description = ?2")
    Product findByNameOrDescUsingIndexPosition(String name, String desc);

    @Query("SELECT p FROM  Product p WHERE p.name = :name OR p.description = :desc")
    Product findByNameOrDescUsingNamedParam(@Param("name") String name, @Param("desc") String desc);

    @Query(value = "SELECT * FROM  products p WHERE p.name = ?1 OR p.description = ?2", nativeQuery = true)
    Product findByNameOrDescUsingIndexPositionNativeSQLQuery(String name, String desc);

    @Query(value = "SELECT * FROM  products p WHERE p.name = :name OR p.description = :desc", nativeQuery = true)
    Product findByNameOrDescUsingNamedParamNativeSQLQuery(@Param("name") String name, @Param("desc") String desc);

    Product findBySku(@Param("sku") String sku);

    List<Product> findByPrice(@Param("price") BigDecimal price);

    @Query(nativeQuery = true)
    List<Product> findAllProductNameByDescOrder();

    @Query(nativeQuery = true)
    List<Product> findHighestPriceProducts();
}
