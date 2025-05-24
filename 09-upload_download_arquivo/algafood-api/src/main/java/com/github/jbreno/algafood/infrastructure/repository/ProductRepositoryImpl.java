package com.github.jbreno.algafood.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.model.PhotoProduct;
import com.github.jbreno.algafood.domain.repository.ProductRepositoryQueries;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public PhotoProduct save(PhotoProduct photo) {
		return manager.merge(photo);
	}
}
