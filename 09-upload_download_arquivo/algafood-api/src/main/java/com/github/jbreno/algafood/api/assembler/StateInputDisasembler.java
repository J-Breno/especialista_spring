package com.github.jbreno.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.input.StateInputDTO;
import com.github.jbreno.algafood.domain.model.State;

@Component
public class StateInputDisasembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public State toDomainObject(StateInputDTO stateInput) {
		return modelMapper.map(stateInput, State.class);
	}
	
	public void copyToDomainObject(StateInputDTO stateInputDTO, State state) {
		modelMapper.map(stateInputDTO, state);
	}
	
}
