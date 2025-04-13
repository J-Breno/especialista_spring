package com.github.jbreno.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.api.model.KitchensXmlWrapper;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.repository.KitchenRepository;


@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
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

}
