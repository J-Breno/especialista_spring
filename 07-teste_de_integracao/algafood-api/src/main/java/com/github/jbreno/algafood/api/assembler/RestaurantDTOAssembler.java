package com.github.jbreno.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.KitchenDTO;
import com.github.jbreno.algafood.api.model.RestaurantDTO;
import com.github.jbreno.algafood.domain.model.Restaurant;
@Component
public class RestaurantDTOAssembler {
	public RestaurantDTO toModel(Restaurant restaurant) {
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
	
	public List<RestaurantDTO> toCollectionDTO(List<Restaurant> restaurants) {
		return restaurants.stream()
				.map(restaurant -> toModel(restaurant))
				.collect(Collectors.toList());
	}
}
