package com.github.jbreno.algafood.api.model.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInputDTO {

	@NotNull
	@Valid
	private PaymentMethodIdInput paymentMethod;
	@Valid
	@NotNull
	private RestaurantIdInput restaurant;
	@Valid
	@NotNull
	private AddressInput deliveryAddress;
	@Valid
	@NotNull
	@Size(min = 1)
	private List<OrderItemInputDTO> itens;
}
