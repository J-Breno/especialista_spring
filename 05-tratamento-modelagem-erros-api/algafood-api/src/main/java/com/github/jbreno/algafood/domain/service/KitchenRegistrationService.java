package com.github.jbreno.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.EntityNotFoundException;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.repository.KitchenRepository;

@Service
public class KitchenRegistrationService {
	
	private static final String MSG_KITCHEN_IN_USE 
		= "Cozinha de código %d não pode ser removida, pois está em uso";

	private static final String MSG_KITCHEN_NOT_FOUND
		= "Não existe um cadastro de cozinha com código %d";
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}
	
	public void remove (Long id) {
		try {
			kitchenRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(MSG_KITCHEN_NOT_FOUND, id));
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_KITCHEN_IN_USE, id));
		}
	}
	
	public Kitchen searchOrFail(Long id) {
		return kitchenRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(MSG_KITCHEN_NOT_FOUND, id)));
	}
}
