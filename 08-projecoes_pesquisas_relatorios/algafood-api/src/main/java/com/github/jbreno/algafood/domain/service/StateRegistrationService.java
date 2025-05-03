package com.github.jbreno.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.StateNotFoundException;
import com.github.jbreno.algafood.domain.model.State;
import com.github.jbreno.algafood.domain.repository.StateRepository;

@Service
public class StateRegistrationService {
	
	private static final String MSG_STATE_IN_USE 
		= "Estado de código %d não pode ser removida, pois está em uso";

	@Autowired
	private StateRepository stateRepository;
	
	public List<State> list() {
		return stateRepository.findAll();
	}
	
	public State search(Long id) {
		return searchOrFail(id);
	}
	
	@Transactional	
	public State save(State state) {
		try {
			return stateRepository.save(state);
		}
		catch(EmptyResultDataAccessException e) {
			throw new StateNotFoundException(state.getId());
		}
	}
	// ALTER TABLE tb_restaurant ADD active tinyint(1) NOT NULL; UPDATE tb_restaurant SET active = true;
	@Transactional
	public void remove (Long id) {
		try {
			stateRepository.deleteById(id);
			stateRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new StateNotFoundException(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_STATE_IN_USE, id));
		}
	}
	
	public State searchOrFail(Long id) {
		return stateRepository.findById(id)
				.orElseThrow(() -> new StateNotFoundException(id));
	}
}