package com.github.jbreno.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jbreno.algafood.domain.exception.EntityNotFoundException;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.repository.KitchenRepository;
import com.github.jbreno.algafood.domain.repository.RestaurantRepository;

@Service
public class RestaurantRegistrationService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public List<Restaurant> list() {
		return restaurantRepository.findAll();
	}

	public Restaurant search(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				String.format("Não existe cadastro de cozinha com código %d", id)));
		return restaurant;
	}
	
	public Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenRepository.findById(kitchenId)
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("Não existe cadastro de cozinha com código %d", kitchenId)));
		
		restaurant.setKitchen(kitchen);
		
		return restaurantRepository.save(restaurant);
	}
}
