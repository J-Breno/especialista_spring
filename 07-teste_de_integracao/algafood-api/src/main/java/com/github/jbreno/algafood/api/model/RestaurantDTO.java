package com.github.jbreno.algafood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RestaurantDTO {
	private Long id;
	private String name;
	private BigDecimal shippingFee;
	private KitchenDTO kitchen;
	private Boolean active;
	private AddressDTO address;
}
