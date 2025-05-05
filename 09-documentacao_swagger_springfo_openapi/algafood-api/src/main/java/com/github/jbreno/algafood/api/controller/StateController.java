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

import com.github.jbreno.algafood.api.assembler.StateDTOAssembler;
import com.github.jbreno.algafood.api.assembler.StateInputDisasembler;
import com.github.jbreno.algafood.api.model.StateDTO;
import com.github.jbreno.algafood.api.model.input.StateInputDTO;
import com.github.jbreno.algafood.api.openapi.controller.StateControllerOpenApi;
import com.github.jbreno.algafood.domain.model.State;
import com.github.jbreno.algafood.domain.service.StateRegistrationService;

@RestController
@RequestMapping("/states")
public class StateController implements StateControllerOpenApi{
	
	@Autowired
	private StateRegistrationService stateService;
	
	@Autowired
	private StateInputDisasembler stateInputDisasembler;
	
	@Autowired
	private StateDTOAssembler stateDTOAssembler;
	
	@GetMapping
	public List<StateDTO> list() {
		return stateDTOAssembler.toCollectionDTO(stateService.list());
	}
	
	@GetMapping("/{id}")
	public StateDTO search(@PathVariable Long id){
		return stateDTOAssembler.toModel(stateService.searchOrFail(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StateDTO add(@RequestBody @Valid StateInputDTO stateInputDTO) {
		State state =  stateInputDisasembler.toDomainObject(stateInputDTO);
		return stateDTOAssembler.toModel(stateService.save(state));
	}
	
	@PutMapping("/{id}")
	public StateDTO update(@PathVariable Long id,@RequestBody @Valid StateInputDTO state) {
		State currentState = stateService.searchOrFail(id);
		
		stateInputDisasembler.copyToDomainObject(state, currentState);
		
		return stateDTOAssembler.toModel(stateService.save(currentState));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		stateService.remove(id);
	}
}
