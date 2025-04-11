package com.github.jbreno.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.model.Kitchen;

@Component
public class KitchenRegistration {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Kitchen> list() {
		return manager.createQuery("from Kitchen", Kitchen.class)
				.getResultList();
	}
	
	public Kitchen search(Long id) {
		return manager.find(Kitchen.class, id);
	}
	
	@Transactional
	public Kitchen add(Kitchen kitchen) {
		return manager.merge(kitchen);
	}
}
