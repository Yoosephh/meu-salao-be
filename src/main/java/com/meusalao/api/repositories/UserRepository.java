package com.meusalao.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meusalao.api.models.User;

public interface UserRepository extends JpaRepository<User, String> {
  User findByEmail(String email);
}
