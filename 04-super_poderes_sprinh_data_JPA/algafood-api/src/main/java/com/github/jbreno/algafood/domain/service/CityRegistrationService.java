package com.github.jbreno.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.EntityNotFoundException;
import com.github.jbreno.algafood.domain.model.City;
import com.github.jbreno.algafood.domain.model.State;
import com.github.jbreno.algafood.domain.repository.CityRepository;
import com.github.jbreno.algafood.domain.repository.StateRepository;

@Service
public class CityRegistrationService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	public List<City> list() {
		return cityRepository.all();
	}
	
	public City search(Long id) {
		return cityRepository.search(id);
	}
	
	public City save(City city) {
		Long stateId = city.getState().getId();
		State state = stateRepository.search(stateId);
		
		if(state == null) {
			throw new EntityNotFoundException(
					String.format("Não existe cadastro de cozinha com código %d", stateId));
		}
		
		city.setState(state);
		
		return cityRepository.save(city);
	}
	
	public void remove (Long id) {
		try {
			cityRepository.remove(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format("Não existe um cadastro de cidade com código %d", id));
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("codade de código %d não pode ser removida, pois está em uso", id));
		}
		
		
	}
	
}