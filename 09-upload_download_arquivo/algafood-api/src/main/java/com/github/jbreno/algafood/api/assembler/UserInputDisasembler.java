package com.github.jbreno.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.input.UserWithPasswordInputDTO;
import com.github.jbreno.algafood.api.model.input.UserWithoutInputDTO;
import com.github.jbreno.algafood.domain.model.User;

@Component
public class UserInputDisasembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public User toDomainObjectWithPassword(UserWithPasswordInputDTO userInput) {
		return modelMapper.map(userInput, User.class);
	}
	
	public void copyToDomainObjectWithPassword(UserWithPasswordInputDTO userInputDTO, User user) {
		modelMapper.map(userInputDTO, user);
	}
	
	public User toDomainObjectWithout(UserWithoutInputDTO userInput) {
		return modelMapper.map(userInput, User.class);
	}
	
	public void copyToDomainObjectWithout(UserWithoutInputDTO userInputDTO, User user) {
		modelMapper.map(userInputDTO, user);
	}
}
