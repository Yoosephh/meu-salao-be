package com.meusalao.api.dtos.Orders;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderRequestDTO {
  @NotNull(message = "Table number is required")
  private Integer tableNumber;
  @NotNull(message = "Command number is required")
  private Integer commandNumber;
  private String description;
  @NotNull(message = "Amount is required")
  private Integer amount;
  @NotNull(message = "Price per item is required")
  private BigDecimal pricePerItem;

}
