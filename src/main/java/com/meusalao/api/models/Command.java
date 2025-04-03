package com.meusalao.api.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import jakarta.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
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
    private Integer commandNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommandStatus status;

    @Column(nullable = false)
    private LocalDateTime dateOpenning;

    private LocalDateTime dateClosing;

    @OneToMany(mappedBy = "command", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

    public enum CommandStatus {
        OPEN, CLOSED
    }

    @Transient
    public BigDecimal getTotalValue() {
        return orders.stream()
            .map(Orders::getTotalValue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
