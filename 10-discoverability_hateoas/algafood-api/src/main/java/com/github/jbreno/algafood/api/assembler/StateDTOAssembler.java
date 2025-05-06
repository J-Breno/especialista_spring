package com.github.jbreno.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.StateDTO;
import com.github.jbreno.algafood.domain.model.State;
@Component
public class StateDTOAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public StateDTO toModel(State state) {
		return modelMapper.map(state, StateDTO.class);
	}
	
	public List<StateDTO> toCollectionDTO(List<State> states) {
		return states.stream()
				.map(state -> toModel(state))
				.collect(Collectors.toList());
	}
}
