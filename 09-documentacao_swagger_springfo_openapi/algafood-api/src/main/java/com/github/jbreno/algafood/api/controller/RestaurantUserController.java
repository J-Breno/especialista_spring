package com.github.jbreno.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbreno.algafood.api.assembler.UserDTOAssembler;
import com.github.jbreno.algafood.api.model.UserDTO;
import com.github.jbreno.algafood.api.openapi.controller.RestaurantUserControllerOpenApi;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.service.RestaurantRegistrationService;

@RestController
@RequestMapping(value = "/restaurants/{id}/responsible")
public class RestaurantUserController implements RestaurantUserControllerOpenApi{
	
	@Autowired
	private RestaurantRegistrationService restaurantService;
	
	@Autowired
	private UserDTOAssembler userDTOAssembler;
	
	
	@GetMapping
	public List<UserDTO> list(@PathVariable Long id) {
		Restaurant restaurant = restaurantService.searchOrFail(id);
		return userDTOAssembler.toCollectionDTO(restaurant.getResponsible());
	}	
	
	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable Long id, @PathVariable Long userId) {
		restaurantService.desassociateResponsible(id, userId);
	}
	
	@PutMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associate(@PathVariable Long id, @PathVariable Long userId) {
		restaurantService.associateResponsible(id, userId);
	}	
}
