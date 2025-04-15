package org.unicesumar.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "sale_items")
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "transaction_key")
    private UUID transactionKey;

    public SaleItem (Product product, int quantity, double price, UUID transactionKey) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.transactionKey = UUID.randomUUID();
    }
}
