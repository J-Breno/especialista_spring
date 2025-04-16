package com.github.jbreno.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.domain.exception.EntityNotFoundException;
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
	public ResponseEntity<Restaurant> search(@PathVariable Long id) {
		Restaurant restaurant = restaurantService.search(id);
		
		if(restaurant != null) {
			return ResponseEntity.ok(restaurant);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody Restaurant restaurant) {
		try {
		  restaurant = restaurantService.save(restaurant);
		  return ResponseEntity.status(HttpStatus.CREATED)
				  .body(restaurant);
		} catch(EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
