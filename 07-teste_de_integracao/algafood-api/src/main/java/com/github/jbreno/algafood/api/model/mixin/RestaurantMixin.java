package com.github.jbreno.algafood.api.model.mixin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.jbreno.algafood.domain.model.Address;
import com.github.jbreno.algafood.domain.model.Kitchen;
import com.github.jbreno.algafood.domain.model.PaymentMethod;
import com.github.jbreno.algafood.domain.model.Product;

public class RestaurantMixin {

	@JsonIgnoreProperties(value = "name", allowGetters = true)
	private Kitchen kitchen;
	
	@JsonIgnore
	private Address address;
	
	@JsonIgnore
	private LocalDateTime registrationDate;
	
	@JsonIgnore
	private LocalDateTime updateDate;
	
	@JsonIgnore
	private List<PaymentMethod> paymentsMethod = new ArrayList<>();
	

	@JsonIgnore
	private List<Product> products = new ArrayList<>();
}
