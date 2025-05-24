package com.github.jbreno.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.api.assembler.UserDTOAssembler;
import com.github.jbreno.algafood.api.assembler.UserInputDisasembler;
import com.github.jbreno.algafood.api.model.UserDTO;
import com.github.jbreno.algafood.api.model.input.PasswordInputDTO;
import com.github.jbreno.algafood.api.model.input.UserWithPasswordInputDTO;
import com.github.jbreno.algafood.api.model.input.UserWithoutInputDTO;
import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.PasswordNotFoundException;
import com.github.jbreno.algafood.domain.exception.UserNotFoundException;
import com.github.jbreno.algafood.domain.model.User;
import com.github.jbreno.algafood.domain.service.UserRegistrationService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserRegistrationService userService;
	
	@Autowired
	private UserDTOAssembler userDTOAssembler;
	
	@Autowired
	private UserInputDisasembler userInputDisasembler;
	
	
	@GetMapping
	public List<UserDTO> list() {
		return userDTOAssembler.toCollectionDTO(userService.list());
	}
	
	@GetMapping("/{id}")
	public UserDTO search(@PathVariable Long id) {
		User user = userService.searchOrFail(id);
		
		return userDTOAssembler.toModel(user);
	}

	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO add(@RequestBody @Valid UserWithPasswordInputDTO userInput) {
		try {
			User user =  userInputDisasembler.toDomainObjectWithPassword(userInput);
			return userDTOAssembler.toModel(userService.save(user));
		}
		catch(UserNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	public UserDTO update(@PathVariable Long id,@RequestBody @Valid UserWithoutInputDTO userInputDTO) {
		try {
			User currentUser = userService.searchOrFail(id);
			
			userInputDisasembler.copyToDomainObjectWithout(userInputDTO, currentUser);
			
			return userDTOAssembler.toModel(userService.save(currentUser));
		}
		catch(UserNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		userService.remove(id);
	}
	
	@PutMapping("/{id}/password")
	public UserWithPasswordInputDTO updatePassword(@PathVariable Long id, @RequestBody @Valid PasswordInputDTO passwordInputDTO) {
		try {
			User currentUser = userService.searchOrFail(id);
			if(!passwordInputDTO.getPassword().equals(currentUser.getPassword())) {
				throw new PasswordNotFoundException(id);
			} else {
				currentUser.setPassword(passwordInputDTO.getNewPassword());
			}
			
			return userDTOAssembler.toModelWithPassword(userService.save(currentUser));
		} catch (UserNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
}
