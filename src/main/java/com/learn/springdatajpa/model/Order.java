package com.learn.springdatajpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders", schema = "ecommerce", uniqueConstraints = {
        @UniqueConstraint(name = "order_tracking_number", columnNames = "orderTrackingNumber")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String orderTrackingNumber;

    private int totalQty;

    private BigDecimal totalPrice;

    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
//    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
//    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Set<OrderItem> orderItems;

    public void setTotalOrderPriceAndQty() {
        BigDecimal totalPrice = new BigDecimal(0);
        int totalQty = 0;
        for (OrderItem orderItem : this.getOrderItems()) {
            totalPrice = totalPrice.add(orderItem.getPrice());
            totalQty++;
        }
        this.setTotalPrice(totalPrice);
        this.setTotalQty(totalQty);
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderTrackingNumber='" + orderTrackingNumber + '\'' +
                ", totalQty=" + totalQty +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", billingAddress= [ id : " + billingAddress.getId() +
                ", street : " + billingAddress.getStreet() +
                ", city : " + billingAddress.getCity() +
                ", country : " + billingAddress.getCountry() +
                ", zipcode : " + billingAddress.getZipCode() +
                '}';
    }
}
