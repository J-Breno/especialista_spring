package com.github.jbreno.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantInputDTO {
	@NotBlank
	private String name;
	@NotNull
	@PositiveOrZero
	private BigDecimal shippingFee;
	@Valid
	@NotNull
	private KitchenIdInput kitchen;
	
	@Valid
	@NotNull
	private AddressInput address;
}
