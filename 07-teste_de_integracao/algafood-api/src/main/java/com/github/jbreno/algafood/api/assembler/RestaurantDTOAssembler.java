package com.github.jbreno.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.RestaurantDTO;
import com.github.jbreno.algafood.domain.model.Restaurant;
@Component
public class RestaurantDTOAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public RestaurantDTO toModel(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantDTO.class);
	}
	
	public List<RestaurantDTO> toCollectionDTO(List<Restaurant> restaurants) {
		return restaurants.stream()
				.map(restaurant -> toModel(restaurant))
				.collect(Collectors.toList());
	}
}
