package com.github.jbreno.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.EntityNotFoundException;
import com.github.jbreno.algafood.domain.model.State;
import com.github.jbreno.algafood.domain.repository.StateRepository;

@Service
public class StateRegistrationService {
	
	@Autowired
	private StateRepository stateRepository;
	
	public List<State> list() {
		return stateRepository.findAll();
	}
	
	public State search(Long id) {
		Optional<State> state =  stateRepository.findById(id);
		return state.get();
	}
	
	public State save(State state) {
		return stateRepository.save(state);
	}
	
	public void remove (Long id) {
		try {
			stateRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("Não existe um cadastro de cozinha com código %d", id));
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
		}
	}
	
}