package com.github.jbreno.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.model.City;
import com.github.jbreno.algafood.domain.repository.CityRepository;
@Repository
public class CityRepositoryImpl implements CityRepository{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<City> all() {
		return manager.createQuery("from City", City.class)
				.getResultList();
	}
	
	@Override
	public City search(Long id) {
		return manager.find(City.class, id);
	}
	
	@Override
	@Transactional
	public City save(City city) {
		return manager.merge(city);
	}
	
	@Override
	@Transactional
	public void remove(Long id) {
		City city = search(id);
		if(city == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(city);
	}
}