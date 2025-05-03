package com.github.jbreno.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.input.GroupInputDTO;
import com.github.jbreno.algafood.domain.model.Group;

@Component
public class GroupInputDisasembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Group toDomainObject(GroupInputDTO groupInput) {
		return modelMapper.map(groupInput, Group.class);
	}
	
	public void copyToDomainObject(GroupInputDTO groupInputDTO, Group group) {
		modelMapper.map(groupInputDTO, group);
	}
	
}
