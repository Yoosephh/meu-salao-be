package com.meusalao.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meusalao.api.models.Command;
import com.meusalao.api.models.Tables;

public interface CommandRepository extends JpaRepository<Command, Long> {
  Optional<Command> findByTableAndCommandNumber(Tables table, Integer commandNumber);
}
