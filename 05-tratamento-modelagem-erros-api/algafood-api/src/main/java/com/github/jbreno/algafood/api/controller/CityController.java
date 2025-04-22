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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public City search(@PathVariable Long id) {
		return cityService.searchOrFail(id);
	}
	
	@PostMapping
	public ResponseEntity<City> add(@RequestBody City city) {
		city = cityService.save(city);
		  return ResponseEntity.status(HttpStatus.CREATED)
				  .body(city);
	}
	
	@PutMapping("/{id}")
	public City update(@PathVariable Long id,@RequestBody City city) {
		City currentCity = cityService.searchOrFail(id);
		
		BeanUtils.copyProperties(city, currentCity, "id", "paymentsMethod");
		
		return cityService.save(currentCity);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		cityService.remove(id);
	}
	
}