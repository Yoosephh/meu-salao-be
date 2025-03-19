package com.meusalao.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("tables")
public class TableController {
  
  @PostMapping("/add")
  public ResponseEntity<String> postMethodName() {
      
      
      return ResponseEntity.status(HttpStatus.CREATED).body("Table added.");
  }
  
}
