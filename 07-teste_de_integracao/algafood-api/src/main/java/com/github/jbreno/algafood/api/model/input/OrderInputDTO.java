package com.github.jbreno.algafood.api.model.input;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.github.jbreno.algafood.api.model.AddressDTO;
import com.github.jbreno.algafood.api.model.OrderItemDTO;
import com.github.jbreno.algafood.api.model.RestaurantResumDTO;
import com.github.jbreno.algafood.api.model.UserDTO;
import com.github.jbreno.algafood.domain.model.OrderStatus;
import com.github.jbreno.algafood.domain.model.PaymentMethod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInputDTO {
	@PositiveOrZero
	@NotBlank
	private BigDecimal subtotal;
	@PositiveOrZero
	@NotBlank
	private BigDecimal shippingFee;
	@NotBlank
	@PositiveOrZero
	private BigDecimal totalValue;
	@NotBlank
	private LocalDateTime dateCreated;
	@NotBlank
	private LocalDateTime confirmationDate;
	@NotBlank
	private LocalDateTime cancelletionDate;
	@NotBlank
	private LocalDateTime deliveryDate;
	@NotNull
	private PaymentMethod paymentMethod;
	@Valid
	@NotNull
	private RestaurantResumDTO restaurant;
	private UserDTO client;
	@Valid
	@NotNull
	private AddressDTO deliveryAddress;
	@NotBlank
	private OrderStatus status;
	@Valid
	@NotNull
	private List<OrderItemDTO> itens;
}
