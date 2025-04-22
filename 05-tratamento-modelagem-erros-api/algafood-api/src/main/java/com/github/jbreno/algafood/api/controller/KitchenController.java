package com.github.jbreno.algafood.api.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.server.ResponseStatusException;

import com.github.jbreno.algafood.domain.exception.EntityInUseException;
import com.github.jbreno.algafood.domain.exception.EntityNotFoundException;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.repository.KitchenRepository;
import com.github.jbreno.algafood.domain.service.KitchenRegistrationService;


@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private KitchenRegistrationService kitchenService;
	
	@GetMapping
	public List<Kitchen> list() {
		return kitchenRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Kitchen> search(@PathVariable Long id) {
		Optional<Kitchen> kitchen = kitchenRepository.findById(id);
		
		if(kitchen.isPresent()) {
			return ResponseEntity.ok(kitchen.get());
		}
//		return ResponseEntity.status(HttpStatus.OK).body(kitchen);
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen add(@RequestBody Kitchen kitchen) {
		return kitchenService.save(kitchen);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {	
		Optional<Kitchen> kitchen2 = kitchenRepository.findById(id);
		
		if(kitchen2.isPresent()) {
			BeanUtils.copyProperties(kitchen, kitchen2.get(), "id", "restaurants");
			Kitchen kitchenSave = kitchenService.save(kitchen2.get());
			return ResponseEntity.ok(kitchenSave);
		} 
			
		return ResponseEntity.notFound().build();
	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> remove(@PathVariable Long id) {
//		try {
//			kitchenService.remove(id);
//			return ResponseEntity.noContent().build();		
//		} catch (EntityNotFoundException e) {
//			return ResponseEntity.notFound().build();
//		}
//		catch (EntityInUseException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//		}
//	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		kitchenService.remove(id);
	}
}
