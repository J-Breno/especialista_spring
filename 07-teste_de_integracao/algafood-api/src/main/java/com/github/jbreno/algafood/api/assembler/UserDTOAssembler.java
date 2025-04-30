package com.github.jbreno.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbreno.algafood.api.model.UserDTO;
import com.github.jbreno.algafood.api.model.input.UserWithPasswordInputDTO;
import com.github.jbreno.algafood.domain.model.User;
@Component
public class UserDTOAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public UserDTO toModel(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
	
	public UserWithPasswordInputDTO toModelWithPassword(User user) {
		return modelMapper.map(user, UserWithPasswordInputDTO.class);
	}
	
	public List<UserDTO> toCollectionDTO(List<User> users) {
		return users.stream()
				.map(user -> toModel(user))
				.collect(Collectors.toList());
	}
}
