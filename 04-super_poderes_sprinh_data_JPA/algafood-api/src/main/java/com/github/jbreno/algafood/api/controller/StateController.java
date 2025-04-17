package com.github.jbreno.algafood.api.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.EntityNotFoundException;
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
	public ResponseEntity<State> search(@PathVariable Long id){
		State state = stateService.search(id);
		
		if(state != null) {
			return ResponseEntity.ok(state);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody State state) {
		try {
		  state = stateService.save(state);
		  return ResponseEntity.status(HttpStatus.CREATED)
				  .body(state);
		} catch(EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody State state) {
		try {
			State state2 = stateService.search(id);
			if(state2 != null) { 
				BeanUtils.copyProperties(state, state2, "id");
				stateService.save(state2);
				return ResponseEntity.ok(state2);
			}
			
			return ResponseEntity.notFound().build();	
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) {
		try {
			stateService.remove(id);
			return ResponseEntity.noContent().build();		
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
