package com.github.jbreno.algafood.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.api.model.KitchenDTO;
import com.github.jbreno.algafood.api.model.RestaurantDTO;
import com.github.jbreno.algafood.api.model.input.RestaurantInputDTO;
import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.RestaurantNotFoundException;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.service.RestaurantRegistrationService;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantRegistrationService restaurantService;
	
	
	@GetMapping
	public List<RestaurantDTO> list() {
		return toCollectionDTO(restaurantService.list());
	}
	
	@GetMapping("/{id}")
	public RestaurantDTO search(@PathVariable Long id) {
		Restaurant restaurant = restaurantService.searchOrFail(id);
		
		return toModel(restaurant);
	}

	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestaurantDTO add(@RequestBody @Valid RestaurantInputDTO restaurantInput) {
		try {
			Restaurant restaurant = toDomainObject(restaurantInput);
			return toModel(restaurantService.save(restaurant));
		}
		catch(RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}	
	@PutMapping("/{id}")
	public RestaurantDTO update(@PathVariable Long id,@RequestBody @Valid RestaurantInputDTO restaurantInputDTO) {
		try {
			Restaurant restaurant = toDomainObject(restaurantInputDTO);
			Restaurant currentRestaurant = restaurantService.searchOrFail(id);
			
			BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "paymentsMethod", "address", "registrationDate", "products");
			currentRestaurant = restaurantService.save(currentRestaurant);
			
			return toModel(restaurantService.save(currentRestaurant));
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
	
	private RestaurantDTO toModel(Restaurant restaurant) {
		KitchenDTO kitchenDTO = new KitchenDTO();
		kitchenDTO.setId(restaurant.getKitchen().getId());
		kitchenDTO.setName(restaurant.getKitchen().getName());
		
		RestaurantDTO restaurantDTO = new RestaurantDTO();
		restaurantDTO.setId(restaurant.getId());
		restaurantDTO.setName(restaurant.getName());
		restaurantDTO.setShippingFee(restaurant.getShippingFee());
		restaurantDTO.setKitchen(kitchenDTO);
		return restaurantDTO;
	}
	
	private List<RestaurantDTO> toCollectionDTO(List<Restaurant> restaurants) {
		return restaurants.stream()
				.map(restaurant -> toModel(restaurant))
				.collect(Collectors.toList());
	}
	
	private Restaurant toDomainObject(RestaurantInputDTO restaurantInput) {
		Restaurant restaurant = new Restaurant();
		restaurant.setName(restaurantInput.getName());
		restaurant.setShippingFee(restaurantInput.getShippingFee());
		
		Kitchen kitchen = new Kitchen();
		kitchen.setId(restaurantInput.getKitchen().getId());
		
		restaurant.setKitchen(kitchen);
		return restaurant;	
	}
}
