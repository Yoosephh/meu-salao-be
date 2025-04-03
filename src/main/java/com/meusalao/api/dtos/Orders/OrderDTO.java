package com.meusalao.api.dtos.Orders;

import java.math.BigDecimal;

import com.meusalao.api.models.Orders;

public class OrderDTO {
    private String description;
    private int amount;
    private BigDecimal totalValue;

    public OrderDTO(Orders order) {
        this.description = order.getDescription();
        this.amount = order.getAmount();
        this.totalValue = order.getTotalValue();
    }

}
