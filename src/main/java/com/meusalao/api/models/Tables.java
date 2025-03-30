package com.meusalao.api.models;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Table(name = "tables")
@Entity
public class Tables {
  public enum TableStatus {
    LIVRE, OCUPADA, RESERVADA
  }

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  
  @OneToMany(mappedBy = "table")
  private List<Command> commands = new ArrayList<>();

  @Column(nullable = false, unique = true)
  private int number;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TableStatus status;

  @Column
  private String details;

  public boolean isOccupied() {
    return this.commands.stream().anyMatch(c -> c.getStatus() == Command.CommandStatus.ABERTA);
  }

  public String getId() {
    return id;
  }

  public Integer getNumber() {
    return number;
  }

  public TableStatus getStatus() {
    return status;
  }

  public String getDetails() {
    return details;
  }

  public List<Command> getCommands() {
    return commands;
  }
}
