package com.github.jbreno.algafood.domain.service;

import java.util.List;

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
	
	private static final String MSG_STATE_IN_USE 
		= "Estado de código %d não pode ser removida, pois está em uso";

	private static final String MSG_STATE_NOT_FOUND
		= "Não existe um cadastro de estado com código %d";
	
	@Autowired
	private StateRepository stateRepository;
	
	public List<State> list() {
		return stateRepository.findAll();
	}
	
	public State search(Long id) {
		return searchOrFail(id);
	}
	
	public State save(State state) {
		try {
			return stateRepository.save(state);
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND, state.getId()));
		}
	}
	
	public void remove (Long id) {
		try {
			stateRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(MSG_STATE_NOT_FOUND, id));
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_STATE_IN_USE, id));
		}
	}
	
	public State searchOrFail(Long id) {
		return stateRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_STATE_NOT_FOUND, id)));
	}
}