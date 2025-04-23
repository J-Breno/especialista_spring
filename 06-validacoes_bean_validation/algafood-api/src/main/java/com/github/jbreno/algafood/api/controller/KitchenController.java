package com.github.jbreno.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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
	public Kitchen search(@PathVariable Long id) {
		return kitchenService.searchOrFail(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen add(@RequestBody @Valid Kitchen kitchen) {
		return kitchenService.save(kitchen);
	}
	
	@PutMapping("/{id}")
	public Kitchen update(@PathVariable Long id, @RequestBody @Valid Kitchen kitchen) {	
		Kitchen currentKitchen = kitchenService.searchOrFail(id);
	
		BeanUtils.copyProperties(kitchen, currentKitchen, "id", "restaurants");
		
		return kitchenService.save(currentKitchen);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		kitchenService.remove(id);
	}
}
