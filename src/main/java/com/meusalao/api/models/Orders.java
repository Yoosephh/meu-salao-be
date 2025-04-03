package com.meusalao.api.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@Entity(name="orders")
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;

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

    private LocalDateTime dateOrder;
    private LocalDateTime datePrepare;

    @PrePersist
    @PreUpdate
    public void calculateTotalValue() {
        if (pricePerItem != null && amount != null) {
            this.totalValue = pricePerItem.multiply(BigDecimal.valueOf(amount));
        } else {
            this.totalValue = BigDecimal.ZERO;
        }
    }

    public BigDecimal getTotalValue() {
        return totalValue != null ? totalValue : BigDecimal.ZERO;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setPricePerItem(BigDecimal pricePerItem) {
        this.pricePerItem = pricePerItem;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
    
    public void setStatus(StatusPedido status) {
        this.status = status;
    }


    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
    }

    public enum StatusPedido {
        PENDING, PREPARING, READY, DELIVERED
    }
}



