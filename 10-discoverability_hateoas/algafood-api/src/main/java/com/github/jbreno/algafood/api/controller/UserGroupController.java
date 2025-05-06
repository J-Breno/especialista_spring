package com.github.jbreno.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.api.assembler.GroupDTOAssembler;
import com.github.jbreno.algafood.api.model.GroupDTO;
import com.github.jbreno.algafood.api.openapi.controller.UserGroupControllerOpenApi;
import com.github.jbreno.algafood.domain.model.User;
import com.github.jbreno.algafood.domain.service.UserRegistrationService;

@RestController
@RequestMapping(value = "/users/{id}/groups")
public class UserGroupController implements UserGroupControllerOpenApi{
	
	@Autowired
	private UserRegistrationService userService;
	
	@Autowired
	private GroupDTOAssembler groupDTOAssembler;
	
	
	@GetMapping
	public List<GroupDTO> list(@PathVariable Long id) {
		User user = userService.searchOrFail(id);
		return groupDTOAssembler.toCollectionDTO(user.getGroups());
	}	
	
	@DeleteMapping("/{groupId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable Long id, @PathVariable Long groupId) {
		userService.desassociateGroup(id,groupId);
	}
	
	@PutMapping("/{groupId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associate(@PathVariable Long id, @PathVariable Long groupId) {
		userService.associateGroup(id, groupId);
	}
}
