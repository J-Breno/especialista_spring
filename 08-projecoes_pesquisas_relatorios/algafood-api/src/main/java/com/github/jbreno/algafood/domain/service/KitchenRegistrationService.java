package com.github.jbreno.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.KitchenNotFoundException;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.repository.KitchenRepository;

@Service
public class KitchenRegistrationService {
	
	private static final String MSG_KITCHEN_IN_USE 
		= "Cozinha de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public List<Kitchen> list() {
		return kitchenRepository.findAll();
	}
	
	@Transactional	
	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}
	
	@Transactional
	public void remove (Long id) {
		try {
			kitchenRepository.deleteById(id);
			kitchenRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new KitchenNotFoundException(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_KITCHEN_IN_USE, id));
		}
	}
	
	public Kitchen searchOrFail(Long id) {
		return kitchenRepository.findById(id)
				.orElseThrow(() -> new KitchenNotFoundException(id));
	}
}
