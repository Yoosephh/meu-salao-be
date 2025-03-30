package com.meusalao.api.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.meusalao.api.models.Command;
import com.meusalao.api.models.Tables;

public class TableDTO {
  private String id;
  private int number;
  private String status;
  private List<OrderDTO> orders;

  public void tablesDTO(Tables table) {
    this.id = table.getId();
    this.number = table.getNumber();
    this.status = table.getStatus().name();

    if (table.isOccupied()) {
      this.orders = table.getCommands()
          .stream()
          .filter(c -> c.getStatus() == Command.CommandStatus.ABERTA)
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