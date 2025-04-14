package com.github.jbreno.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.repository.RestaurantRepository;

@Service
public class RestaurantRegistrationService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public List<Restaurant> list() {
		return restaurantRepository.all();
	}

	public Restaurant search(Long id) {
		return restaurantRepository.search(id);
	}
}
