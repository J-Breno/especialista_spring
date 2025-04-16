package com.github.jbreno.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.model.State;
import com.github.jbreno.algafood.domain.repository.StateRepository;
@Component
public class StateRepositoryImpl implements StateRepository{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<State> all() {
		return manager.createQuery("from State", State.class)
				.getResultList();
	}
	
	@Override
	public State search(Long id) {
		return manager.find(State.class, id);
	}
	
	@Override
	@Transactional
	public State save(State state) {
		return manager.merge(state);
	}
	
	@Override
	@Transactional
	public void remove(Long id) {
		State state = search(id);
		if(state == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(state);
	}
}