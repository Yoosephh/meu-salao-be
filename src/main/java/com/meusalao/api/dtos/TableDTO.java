package com.meusalao.api.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.meusalao.api.dtos.Orders.OrderDTO;
import com.meusalao.api.models.Command;
import com.meusalao.api.models.Tables;

import jakarta.validation.constraints.NotNull;

public class TableDTO {
  @NotNull(message = "Table ID is required")
  private String id;
  
  @NotNull(message = "Table number is required")
  private int number;

  @NotNull(message = "Table status is required")
  private String status;
  private List<OrderDTO> orders;

  public void tablesDTO(Tables table) {
    this.id = table.getId();
    this.number = table.getNumber();
    this.status = table.getStatus().name();

    if (table.isOccupied()) {
      this.orders = table.getCommands()
          .stream()
          .filter(c -> c.getStatus() == Command.CommandStatus.OPEN)
          .flatMap(c -> c.getOrders().stream())
          .map(OrderDTO::new)
          .toList();
    }
  }

  public TableDTO(String id, int number, String status) {
    this.id = id;
    this.number = number;
    this.status = status;
  }
}