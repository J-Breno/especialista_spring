package com.github.jbreno.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.RestaurantNotFoundException;
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
		return restaurantService.searchOrFail(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant add(@RequestBody Restaurant restaurant) {
		try {
			return restaurantService.save(restaurant);
		}
		catch(RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}	
	@PutMapping("/{id}")
	public Restaurant update(@PathVariable Long id,@RequestBody Restaurant restaurant) {
		Restaurant currentRestaurant = restaurantService.searchOrFail(id);
		
		BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "paymentsMethod", "address", "registrationDate", "products");
		currentRestaurant = restaurantService.save(currentRestaurant);
		
		try {
			return restaurantService.save(currentRestaurant);
		}
		catch(RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		restaurantService.remove(id);
	}
	
	@PatchMapping
	public Restaurant partiallyUpdate(@PathVariable Long id, @RequestBody Map<String, Object> campos, HttpServletRequest request) {
		Restaurant restaurant2 = restaurantService.search(id);
		
		merge(campos, restaurant2, request);
		
		return update(id, restaurant2);
	}

	private void merge(Map<String, Object> campos, Restaurant restaurant, HttpServletRequest request) {
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			
			
			Restaurant restaurant00 = objectMapper.convertValue(campos, Restaurant.class);
			campos.forEach((nomePropriedade, valorPropriedade) -> {
				Field field = ReflectionUtils.findField(Restaurant.class, nomePropriedade);
				
				field.setAccessible(true);
				
				Object newValue = ReflectionUtils.getField(field, restaurant00);
				
				ReflectionUtils.setField(field, restaurant, newValue);
			});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}
}
