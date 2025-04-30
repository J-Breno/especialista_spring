package com.github.jbreno.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jbreno.algafood.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
