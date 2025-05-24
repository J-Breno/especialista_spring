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

import com.github.jbreno.algafood.api.assembler.GroupDTOAssembler;
import com.github.jbreno.algafood.api.assembler.GroupInputDisasembler;
import com.github.jbreno.algafood.api.model.GroupDTO;
import com.github.jbreno.algafood.api.model.input.GroupInputDTO;
import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.GroupNotFoundException;
import com.github.jbreno.algafood.domain.model.Group;
import com.github.jbreno.algafood.domain.service.GroupRegistrationService;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {
	
	@Autowired
	private GroupRegistrationService groupService;
	
	@Autowired
	private GroupDTOAssembler groupDTOAssembler;
	
	@Autowired
	private GroupInputDisasembler groupInputDisasembler;
	
	
	@GetMapping
	public List<GroupDTO> list() {
		return groupDTOAssembler.toCollectionDTO(groupService.list());
	}
	
	@GetMapping("/{id}")
	public GroupDTO search(@PathVariable Long id) {
		Group group = groupService.searchOrFail(id);
		
		return groupDTOAssembler.toModel(group);
	}

	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GroupDTO add(@RequestBody @Valid GroupInputDTO groupInput) {
		try {
			Group group =  groupInputDisasembler.toDomainObject(groupInput);
			return groupDTOAssembler.toModel(groupService.save(group));
		}
		catch(GroupNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	public GroupDTO update(@PathVariable Long id,@RequestBody @Valid GroupInputDTO groupInputDTO) {
		try {
			Group currentGroup = groupService.searchOrFail(id);
			
			groupInputDisasembler.copyToDomainObject(groupInputDTO, currentGroup);
			
			return groupDTOAssembler.toModel(groupService.save(currentGroup));
		}
		catch(GroupNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		groupService.remove(id);
	}
}
