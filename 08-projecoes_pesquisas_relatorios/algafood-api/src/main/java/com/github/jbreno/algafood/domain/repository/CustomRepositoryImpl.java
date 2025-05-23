package com.github.jbreno.algafood.domain.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID>{
	private EntityManager entityManager;
	
	public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		
		this.entityManager = entityManager;
	}

	@Override
	public void detach(T entity) {
		entityManager.detach(entity);
		
	}
}
