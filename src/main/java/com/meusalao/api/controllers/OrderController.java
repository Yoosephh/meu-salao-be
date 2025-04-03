package com.meusalao.api.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meusalao.api.dtos.Orders.OrderRequestDTO;
import com.meusalao.api.models.Command;
import com.meusalao.api.models.Orders;
import com.meusalao.api.models.Tables;
import com.meusalao.api.repositories.CommandRepository;
import com.meusalao.api.repositories.OrderRepository;
import com.meusalao.api.repositories.TableRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderRepository orderRepository;
  private final CommandRepository commandRepository;
  private final TableRepository tableRepository;

  public OrderController(OrderRepository orderRepository, CommandRepository commandRepository, TableRepository tableRepository) {
    this.orderRepository = orderRepository;
    this.commandRepository = commandRepository;
    this.tableRepository = tableRepository;
  }

  @PostMapping("/create")
  public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO orderRequest) {
    Optional<Tables> optionalTable = tableRepository.findByNumber(orderRequest.getTableNumber());

    if(optionalTable.isEmpty()) {
      return ResponseEntity.badRequest().body("Table not found.");
    }

    Tables table = optionalTable.get();

    Optional<Command> optionalCommand = commandRepository.findByTableAndCommandNumber(table, orderRequest.getCommandNumber());

    Command command;

    if(optionalCommand.isPresent()) {
      command = optionalCommand.get();
    } else {

      command = new Command();
      
      table.setStatus(Tables.TableStatus.OCCUPIED);
      tableRepository.save(table);

      command.setTable(table);
      command.setCommandNumber(orderRequest.getCommandNumber());
      command.setStatus(Command.CommandStatus.OPEN);
      command.setDateOpenning(java.time.LocalDateTime.now());

      commandRepository.save(command);
    }

    Orders order = new Orders();
    order.setCommand(command);
    order.setDescription(orderRequest.getDescription());
    order.setAmount(orderRequest.getAmount());
    order.setPricePerItem(orderRequest.getPricePerItem());
    order.setStatus(Orders.StatusPedido.PENDING);
    order.setDateOrder(java.time.LocalDateTime.now());

    order.calculateTotalValue();
    orderRepository.save(order);

    return ResponseEntity.ok("Order created successfully.");
  }
}
