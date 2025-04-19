package com.github.jbreno.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Restaurant restaurant) {
		try {
			Restaurant restaurant2 = restaurantService.search(id);
			if(restaurant2 != null) { 
				BeanUtils.copyProperties(restaurant, restaurant2, "id", "address");
				restaurantService.save(restaurant2);
				return ResponseEntity.ok(restaurant2);
			}
			
			return ResponseEntity.notFound().build();	
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping
	public ResponseEntity<?> partiallyUpdate(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
		Restaurant restaurant2 = restaurantService.search(id);
		
		if(restaurant2 == null) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, restaurant2);
		
		return update(id, restaurant2);
	}

	private void merge(Map<String, Object> campos, Restaurant restaurant) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant restaurant00 = objectMapper.convertValue(campos, Restaurant.class);
		campos.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, nomePropriedade);
			
			field.setAccessible(true);
			
			Object newValue = ReflectionUtils.getField(field, restaurant00);
			
			ReflectionUtils.setField(field, restaurant, newValue);
		});
	}
}
