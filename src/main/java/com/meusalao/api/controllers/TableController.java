package com.meusalao.api.controllers;

import com.meusalao.api.dtos.TableDTO;
import com.meusalao.api.models.Tables;
import com.meusalao.api.repositories.TableRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tables")
public class TableController {
  private final TableRepository tableRepository;

  public TableController(TableRepository tableRepository) {
      this.tableRepository = tableRepository;
  }

  @GetMapping("/available")
  public List<TableDTO> getAvailableTables() {
    return tableRepository.findByStatus(Tables.TableStatus.LIVRE)
      .stream()
      .map(table -> new TableDTO(table.getId(), table.getNumber(), table.getDetails()))
      .toList();
  }
  
  @PostMapping("/add")
  public ResponseEntity<String> postMethodName() {
      return ResponseEntity.status(HttpStatus.CREATED).body("Table added.");
  }
}
