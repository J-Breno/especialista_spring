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

import com.github.jbreno.algafood.api.assembler.PermissionDTOAssembler;
import com.github.jbreno.algafood.api.model.PermissionDTO;
import com.github.jbreno.algafood.api.openapi.controller.GroupPermissionControllerOpenApi;
import com.github.jbreno.algafood.domain.model.Group;
import com.github.jbreno.algafood.domain.service.GroupRegistrationService;

@RestController
@RequestMapping(value = "/groups/{id}/permissions")
public class GroupPermissionController implements GroupPermissionControllerOpenApi{
	
	@Autowired
	private GroupRegistrationService groupService;
	
	@Autowired
	private PermissionDTOAssembler permissionDTOAssembler;
	
	
	@GetMapping
	public List<PermissionDTO> list(@PathVariable Long id) {
		Group group = groupService.searchOrFail(id);
		return permissionDTOAssembler.toCollectionDTO(group.getPermissions());
	}	
	
	@DeleteMapping("/{permissionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable Long id, @PathVariable Long permissionId) {
		groupService.desassociatePermission(id,permissionId);
	}
	
	@PutMapping("/{permissionId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associate(@PathVariable Long id, @PathVariable Long permissionId) {
		groupService.associatePermission(id, permissionId);
	}
}
