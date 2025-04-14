package com.github.jbreno.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.github.jbreno.algafood.api.model.KitchensXmlWrapper;
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
		return kitchenRepository.all();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public KitchensXmlWrapper listXml() {
		return new KitchensXmlWrapper(kitchenRepository.all());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Kitchen> search(@PathVariable Long id) {
		Kitchen kitchen = kitchenRepository.search(id);
		
		if(kitchen != null) {
			return ResponseEntity.ok(kitchen);
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
	public ResponseEntity<Kitchen> atualizar(@PathVariable Long id, @RequestBody Kitchen kitchen) {	
		Kitchen kitchen2 = kitchenRepository.search(id);
		
		if(kitchen2 != null) {
			BeanUtils.copyProperties(kitchen, kitchen2, "id");
			kitchenRepository.save(kitchen2);
			return ResponseEntity.ok(kitchen2);
		} 
			
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		try {
			kitchenService.remove(id);
			return ResponseEntity.noContent().build();		
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
