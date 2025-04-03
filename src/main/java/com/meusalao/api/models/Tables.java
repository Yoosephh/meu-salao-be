package com.meusalao.api.models;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@Table(name = "tables")
@Entity
public class Tables {
  public enum TableStatus {
    UNOCCUPIED, OCCUPIED, RESERVED
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
    return this.commands.stream().anyMatch(c -> c.getStatus() == Command.CommandStatus.OPEN);
  }
  public boolean isReserved() {
    return this.status == TableStatus.RESERVED;
  }
  public boolean isUnoccupied() {
    return this.status == TableStatus.UNOCCUPIED;
  }
}
