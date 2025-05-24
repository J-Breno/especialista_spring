package com.github.jbreno.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jbreno.algafood.domain.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long>{
}
