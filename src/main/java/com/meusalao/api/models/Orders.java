package com.meusalao.api.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity(name = "orders")
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comanda_id", nullable = false)
    private Command command;

    @Column(nullable = false)
    private String description;

    private Integer amount;

    private BigDecimal pricePerItem;

    private BigDecimal totalValue;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private LocalDateTime dataPedido;
    private LocalDateTime dataPreparo;
    private LocalDateTime dataEntrega;

    @PrePersist
    @PreUpdate
    private void calculateTotalValue() {
        if (pricePerItem != null && amount != null) {
            this.totalValue = pricePerItem.multiply(BigDecimal.valueOf(amount));
        } else {
            this.totalValue = BigDecimal.ZERO;
        }
    }

    public BigDecimal getTotalValue() {
        return totalValue != null ? totalValue : BigDecimal.ZERO;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAmount() {
        return amount;
    }

    public enum StatusPedido {
        PENDENTE, EM_PREPARO, PRONTO, SERVIDO
    }
}



