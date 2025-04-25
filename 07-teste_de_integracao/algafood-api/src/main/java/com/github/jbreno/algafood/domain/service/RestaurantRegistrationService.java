package com.github.jbreno.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.RestaurantNotFoundException;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.repository.RestaurantRepository;

@Service
public class RestaurantRegistrationService {
	
	private static final String MSG_RESTAURANT_IN_USE 
		= "Restaurante de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRegistrationService kitchenService;
	
	public List<Restaurant> list() {
		return restaurantRepository.findAll();
	}

	public Restaurant search(Long id) {
		return searchOrFail(id);
	}
	
	@Transactional
	public Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenService.searchOrFail(kitchenId);
		
		restaurant.setKitchen(kitchen);
		
		return restaurantRepository.save(restaurant);
	}
	
	public void remove (Long id) {
		try {
			restaurantRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RestaurantNotFoundException(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_RESTAURANT_IN_USE, id));
		}
	}
	
	public Restaurant searchOrFail(Long id) {
		return restaurantRepository.findById(id)
				.orElseThrow(() -> new RestaurantNotFoundException(id));
	}
}
