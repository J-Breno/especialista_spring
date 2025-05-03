package com.github.jbreno.algafood.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.github.jbreno.algafood.domain.model.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDTO {
	private Long id;
	private BigDecimal subtotal;
	private BigDecimal shippingFee;
	private BigDecimal totalValue;
	private LocalDateTime dateCreated;
	private LocalDateTime confirmationDate;
	private LocalDateTime cancelletionDate;
	private LocalDateTime deliveryDate;
	private PaymentMethodDTO paymentMethod;
	private RestaurantResumDTO restaurant;
	private UserDTO client;
	private AddressDTO deliveryAddress;
	private OrderStatus status;
	private List<OrderItemDTO> itens;
}
