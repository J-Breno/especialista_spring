package com.github.jbreno.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	public ResponseEntity<Restaurant> add(@RequestBody Restaurant restaurant) {
		restaurant = restaurantService.save(restaurant);
		  return ResponseEntity.status(HttpStatus.CREATED)
				  .body(restaurant);
	}
	
	@PutMapping("/{id}")
	public Restaurant update(@PathVariable Long id,@RequestBody Restaurant restaurant) {
		Restaurant currentRestaurant = restaurantService.searchOrFail(id);
		
		BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "paymentsMethod", "address", "registrationDate", "products");
		currentRestaurant = restaurantService.save(currentRestaurant);
		
		return restaurantService.save(currentRestaurant);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		restaurantService.remove(id);
	}
	
	@PatchMapping
	public Restaurant partiallyUpdate(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
		Restaurant restaurant2 = restaurantService.search(id);
		
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
