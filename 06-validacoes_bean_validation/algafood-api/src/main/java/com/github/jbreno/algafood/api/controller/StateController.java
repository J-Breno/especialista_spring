package com.github.jbreno.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.domain.model.State;
import com.github.jbreno.algafood.domain.service.StateRegistrationService;

@RestController
@RequestMapping("/states")
public class StateController {
	
	@Autowired
	private StateRegistrationService stateService;
	
	@GetMapping
	public List<State> list() {
		return stateService.list();
	}
	
	@GetMapping("/{id}")
	public State search(@PathVariable Long id){
		return stateService.searchOrFail(id);
	}
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody @Valid State state) {
	  state = stateService.save(state);
	  return ResponseEntity.status(HttpStatus.CREATED)
			  .body(state);
	}
	
	@PutMapping("/{id}")
	public State update(@PathVariable Long id,@RequestBody @Valid State state) {
		State currentState = stateService.searchOrFail(id);
		
		BeanUtils.copyProperties(state, currentState, "id");
		
		return stateService.save(currentState);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		stateService.remove(id);
	}
}
