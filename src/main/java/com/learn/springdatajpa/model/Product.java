package com.learn.springdatajpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "Product.findBySku", query = "SELECT p FROM Product p WHERE p.sku = :sku"),
        @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")

})
@NamedNativeQueries({
        @NamedNativeQuery(name = "Product.findAllProductNameByDescOrder", query = "SELECT * FROM products p  ORDER BY p.name DESC", resultClass = Product.class),
        @NamedNativeQuery(name = "Product.findHighestPriceProducts", query = "SELECT DISTINCT * FROM products p  ORDER BY p.name DESC LIMIT 1", resultClass = Product.class)
})
@Table(name = "products",
        schema = "ecommerce",
        uniqueConstraints = {
                @UniqueConstraint(name = "sku_unique", columnNames = "stock_keeping_unit"),
                @UniqueConstraint(name = "name_unique", columnNames = "name")
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence_generator")
    @SequenceGenerator(name = "product_sequence_generator", sequenceName = "product_sequence_name", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "stock_keeping_unit", nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;

    private String description;

    private BigDecimal price;

    private boolean isActive;

    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private ProductCategory productCategory;


}
