package com.github.jbreno.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.input.KitchenInputDTO;
import com.github.jbreno.algafood.domain.model.Kitchen;

@Component
public class KitchenInputDisasembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Kitchen toDomainObject(KitchenInputDTO kitchenInput) {
		return modelMapper.map(kitchenInput, Kitchen.class);
	}
	
	public void copyToDomainObject(KitchenInputDTO kitchenInputDTO, Kitchen kitchen) {
		modelMapper.map(kitchenInputDTO, kitchen);
	}
	
}
