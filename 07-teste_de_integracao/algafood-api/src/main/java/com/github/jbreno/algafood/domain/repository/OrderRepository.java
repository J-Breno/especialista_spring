package com.github.jbreno.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jbreno.algafood.domain.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
