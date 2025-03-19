package com.meusalao.api.models;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Tables {
  public enum TableStatus {
    LIVRE, OCUPADA, RESERVADA
  }

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  
  @Column(nullable = false, unique = true)
  private int number;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TableStatus status;

  private String details; 

  private LocalDateTime dateOpening;
  private LocalDateTime dateClosing; 
}
