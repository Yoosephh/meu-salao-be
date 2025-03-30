package com.meusalao.api.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;

@Entity(name = "commands")
@Table(name = "commands")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private Tables table;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommandStatus status;

    @Column(nullable = false)
    private LocalDateTime dataAbertura;

    private LocalDateTime dataFechamento;

    @OneToMany(mappedBy = "command", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

    public enum CommandStatus {
        ABERTA, FECHADA
    }

    @Transient
    public BigDecimal getValorTotal() {
        return orders.stream()
            .map(Orders::getTotalValue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public CommandStatus getStatus() {
        return status;
    }

    public List<Orders> getOrders() {
        return orders;
    }
}
