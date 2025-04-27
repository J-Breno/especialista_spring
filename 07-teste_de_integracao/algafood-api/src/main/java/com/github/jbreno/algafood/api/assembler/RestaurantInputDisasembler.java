package com.github.jbreno.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.input.RestaurantInputDTO;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.model.Restaurant;

@Component
public class RestaurantInputDisasembler {
	public Restaurant toDomainObject(RestaurantInputDTO restaurantInput) {
		Restaurant restaurant = new Restaurant();
		restaurant.setName(restaurantInput.getName());
		restaurant.setShippingFee(restaurantInput.getShippingFee());
		
		Kitchen kitchen = new Kitchen();
		kitchen.setId(restaurantInput.getKitchen().getId());
		
		restaurant.setKitchen(kitchen);
		return restaurant;	
	}
}
