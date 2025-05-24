package com.github.jbreno.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.input.CityInputDTO;
import com.github.jbreno.algafood.domain.model.City;
import com.github.jbreno.algafood.domain.model.State;

@Component
public class CityInputDisasembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public City toDomainObject(CityInputDTO cityInput) {
		return modelMapper.map(cityInput, City.class);
	}
	
	public void copyToDomainObject(CityInputDTO cityInputDTO, City city) {
		city.setState(new State());
		modelMapper.map(cityInputDTO, city);
	}
	
}
