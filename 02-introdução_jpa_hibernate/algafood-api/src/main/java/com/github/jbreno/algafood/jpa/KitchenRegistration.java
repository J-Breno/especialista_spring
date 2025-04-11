package com.github.jbreno.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.domain.model.Kitchen;

@Component
public class KitchenRegistration {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Kitchen> listar() {
		return manager.createQuery("from Kitchen", Kitchen.class)
				.getResultList();
	}
}
