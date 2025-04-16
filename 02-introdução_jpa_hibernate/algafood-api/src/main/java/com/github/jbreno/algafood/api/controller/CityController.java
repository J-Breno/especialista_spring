package com.github.jbreno.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.domain.exception.EntityNotFoundException;
import com.github.jbreno.algafood.domain.model.City;
import com.github.jbreno.algafood.domain.service.CityRegistrationService;

@RestController
@RequestMapping("/cities")
public class CityController {
	
	@Autowired
	private CityRegistrationService cityService;
	
	@GetMapping
	public List<City> list() {
		return cityService.list();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<City> search(@PathVariable Long id) {
		City City = cityService.search(id);
		
		if(City != null) {
			return ResponseEntity.ok(City);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody City City) {
		try {
		  City = cityService.save(City);
		  return ResponseEntity.status(HttpStatus.CREATED)
				  .body(City);
		} catch(EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody City city) {
		try {
			City city2 = cityService.search(id);
			if(city2 != null) { 
				BeanUtils.copyProperties(city, city2, "id", "paymentsMethod");
				cityService.save(city2);
				return ResponseEntity.ok(city2);
			}
			
			return ResponseEntity.notFound().build();	
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}