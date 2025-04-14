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
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}
	
	public void remove (Long id) {
		try {
			kitchenRepository.remove(id);
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
