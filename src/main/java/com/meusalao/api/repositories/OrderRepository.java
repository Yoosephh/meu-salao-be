package com.meusalao.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meusalao.api.models.Orders;


public interface OrderRepository extends JpaRepository<Orders, Long> {

}
