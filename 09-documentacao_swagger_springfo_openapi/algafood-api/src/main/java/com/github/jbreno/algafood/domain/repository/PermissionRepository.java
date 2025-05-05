package com.github.jbreno.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jbreno.algafood.domain.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
}
