package com.example.sneakershop.order;

import com.example.sneakershop.basket.Basket;
import com.example.sneakershop.product.Product;
import com.example.sneakershop.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date orderDate;

    @Column(name = "description")
    private String orderDescription;

    @ElementCollection
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "product_amount")
    @CollectionTable(name = "products_table", joinColumns = @JoinColumn(name = "order_id"))
    Map<Product, Integer> productMap;

    private BigDecimal totalPrice;

    public Order(){
        this.productMap = new HashMap<>();
    }

    public void addProducts(Map<Product, Integer> products) {
        products.forEach((product, amount) -> this.productMap.put(product, amount));
    }


}
