package com.github.jbreno.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.input.RestaurantInputDTO;
import com.github.jbreno.algafood.domain.model.Restaurant;

@Component
public class RestaurantInputDisasembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurant toDomainObject(RestaurantInputDTO restaurantInput) {
		return modelMapper.map(restaurantInput, Restaurant.class);
	}
}
