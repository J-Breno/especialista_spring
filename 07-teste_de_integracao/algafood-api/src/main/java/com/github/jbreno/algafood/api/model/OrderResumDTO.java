package com.github.jbreno.algafood.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.github.jbreno.algafood.domain.model.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResumDTO {
	private String code;
	private BigDecimal subtotal;
	private BigDecimal shippingFee;
	private BigDecimal totalValue;
	private LocalDateTime dateCreated;
	private RestaurantResumDTO restaurant;
	private UserDTO client;
	private OrderStatus status;
}
