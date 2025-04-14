package com.github.jbreno.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.service.RestaurantRegistrationService;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantRegistrationService restaurantService;
	
	@GetMapping
	public List<Restaurant> list() {
		return restaurantService.list();
	}
	
	@GetMapping("/{id}")
	public Restaurant search(@PathVariable Long id) {
		return restaurantService.search(id);
	}
}
