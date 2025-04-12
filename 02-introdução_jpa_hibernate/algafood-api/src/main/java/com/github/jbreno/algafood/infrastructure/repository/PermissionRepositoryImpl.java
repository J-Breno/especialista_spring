package com.github.jbreno.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.model.Permission;
import com.github.jbreno.algafood.domain.repository.PermissionRepository;
@Component
public class PermissionRepositoryImpl implements PermissionRepository{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permission> all() {
		return manager.createQuery("from Permission", Permission.class)
				.getResultList();
	}
	
	@Override
	public Permission search(Long id) {
		return manager.find(Permission.class, id);
	}
	
	@Override
	@Transactional
	public Permission save(Permission permission) {
		return manager.merge(permission);
	}
	
	@Override
	@Transactional
	public void remove(Permission permission) {
		permission = search(permission.getId());
		manager.remove(permission);
	}
}