package com.meusalao.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meusalao.api.models.Tables;

public interface TableRepository extends JpaRepository<Tables, String> {
  List<Tables> findByStatus(Tables.TableStatus status);

  Optional<Tables> findByNumber(Integer number);
}
