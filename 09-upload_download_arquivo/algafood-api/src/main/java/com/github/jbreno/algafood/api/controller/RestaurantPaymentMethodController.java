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

import com.github.jbreno.algafood.api.assembler.PaymentMethodDTOAssembler;
import com.github.jbreno.algafood.api.model.PaymentMethodDTO;
import com.github.jbreno.algafood.domain.model.Restaurant;
import com.github.jbreno.algafood.domain.service.RestaurantRegistrationService;

@RestController
@RequestMapping(value = "/restaurants/{id}/payments-method")
public class RestaurantPaymentMethodController {
	
	@Autowired
	private RestaurantRegistrationService restaurantService;
	
	@Autowired
	private PaymentMethodDTOAssembler paymentMethodDTOAssembler;
	
	
	@GetMapping
	public List<PaymentMethodDTO> list(@PathVariable Long id) {
		Restaurant restaurant = restaurantService.searchOrFail(id);
		return paymentMethodDTOAssembler.toCollectionDTO(restaurant.getPaymentsMethod());
	}	
	
	@DeleteMapping("/{paymentMethodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disassociate(@PathVariable Long id, @PathVariable Long paymentMethodId) {
		restaurantService.desassociatePaymentMethod(id, paymentMethodId);
	}
	
	@PutMapping("/{paymentMethodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associate(@PathVariable Long id, @PathVariable Long paymentMethodId) {
		restaurantService.associatePaymentMethod(id, paymentMethodId);
	}
}
