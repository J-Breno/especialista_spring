package com.github.jbreno.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jbreno.algafood.domain.model.State;

public interface StateRepository extends JpaRepository<State, Long>{
}
