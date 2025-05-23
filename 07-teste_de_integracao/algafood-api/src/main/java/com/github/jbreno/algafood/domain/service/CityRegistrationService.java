package com.github.jbreno.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.CityNotFoundException;
import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.model.City;
import com.github.jbreno.algafood.domain.model.State;
import com.github.jbreno.algafood.domain.repository.CityRepository;

@Service
public class CityRegistrationService {
	
	private static final String MSG_CITY_IN_USE 
	= "Cidade de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRegistrationService stateService;
	
	public List<City> list() {
		return cityRepository.findAll();
	}
	
	public City search(Long id) {
		Optional<City> city =  cityRepository.findById(id);
		return city.get();
	}
	
	@Transactional	
	public City save(City city) {
		Long stateId = city.getState().getId();
		State state = stateService.searchOrFail(stateId);
		
		city.setState(state);
		
		return cityRepository.save(city);
	
	}
	
	@Transactional
	public void remove (Long id) {
		try {
			cityRepository.deleteById(id);
			cityRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new CityNotFoundException(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_CITY_IN_USE, id));
		}
	}
	
	public City searchOrFail(Long id) {
		return cityRepository.findById(id)
				.orElseThrow(() -> new CityNotFoundException(id));
	}
	
}