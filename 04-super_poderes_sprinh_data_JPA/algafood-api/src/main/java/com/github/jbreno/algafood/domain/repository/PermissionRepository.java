package com.github.jbreno.algafood.domain.repository;

import java.util.List;

import com.github.jbreno.algafood.domain.model.Permission;

public interface PermissionRepository {
	List<Permission> all();
	Permission search(Long id);
	Permission save(Permission permission);
	void remove(Permission permission);
}
