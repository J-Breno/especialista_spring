package com.github.jbreno.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.repository.RestaurantRepository;
@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurant> all() {
		return manager.createQuery("from Restaurant", Restaurant.class)
				.getResultList();
	}
	
	@Override
	public Restaurant search(Long id) {
		return manager.find(Restaurant.class, id);
	}
	
	@Override
	@Transactional
	public Restaurant save(Restaurant restaurant) {
		return manager.merge(restaurant);
	}
	
	@Override
	@Transactional
	public void remove(Restaurant restaurant) {
		restaurant = search(restaurant.getId());
		manager.remove(restaurant);
	}
}
