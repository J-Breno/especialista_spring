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

import com.github.jbreno.algafood.api.assembler.RestaurantDTOAssembler;
import com.github.jbreno.algafood.api.assembler.RestaurantInputDisasembler;
import com.github.jbreno.algafood.api.model.RestaurantDTO;
import com.github.jbreno.algafood.api.model.input.RestaurantInputDTO;
import com.github.jbreno.algafood.domain.exception.BusinessException;
import com.github.jbreno.algafood.domain.exception.RestaurantNotFoundException;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.service.RestaurantRegistrationService;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantRegistrationService restaurantService;
	
	@Autowired
	private RestaurantDTOAssembler restaurantDTOAssembler;
	
	@Autowired
	private RestaurantInputDisasembler restaurantInputDisasembler;
	
	
	@GetMapping
	public List<RestaurantDTO> list() {
		return restaurantDTOAssembler.toCollectionDTO(restaurantService.list());
	}
	
	@GetMapping("/{id}")
	public RestaurantDTO search(@PathVariable Long id) {
		Restaurant restaurant = restaurantService.searchOrFail(id);
		
		return restaurantDTOAssembler.toModel(restaurant);
	}

	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestaurantDTO add(@RequestBody @Valid RestaurantInputDTO restaurantInput) {
		try {
			Restaurant restaurant =  restaurantInputDisasembler.toDomainObject(restaurantInput);
			return restaurantDTOAssembler.toModel(restaurantService.save(restaurant));
		}
		catch(RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}	
	@PutMapping("/{id}")
	public RestaurantDTO update(@PathVariable Long id,@RequestBody @Valid RestaurantInputDTO restaurantInputDTO) {
		try {
			Restaurant currentRestaurant = restaurantService.searchOrFail(id);
			
			restaurantInputDisasembler.copyToDomainObject(restaurantInputDTO, currentRestaurant);
			
			return restaurantDTOAssembler.toModel(restaurantService.save(currentRestaurant));
		}
		catch(RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {	
		restaurantService.remove(id);
	}
}
