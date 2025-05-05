package com.github.jbreno.algafood.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.github.jbreno.algafood.domain.model.OrderStatus;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDTO {
	@ApiModelProperty(example = "81226e3b-522c-48b3-8749-4b0d9d8e6099", required = true)
	private String code;
	@ApiModelProperty(example = "159.32")
	private BigDecimal subtotal;
	@ApiModelProperty(example = "34.8")
	private BigDecimal shippingFee;
	@ApiModelProperty(example = "200.0")
	private BigDecimal totalValue;
	@ApiModelProperty(example = "2019-21-01T20:34:04Z")
	private LocalDateTime dateCreated;
	@ApiModelProperty(example = "2019-21-01T20:34:04Z")
	private LocalDateTime confirmationDate;
	@ApiModelProperty(example = "2019-21-01T20:34:04Z")
	private LocalDateTime cancelletionDate;
	@ApiModelProperty(example = "2019-21-01T20:34:04Z")
	private LocalDateTime deliveryDate;
	private PaymentMethodDTO paymentMethod;
	private RestaurantResumDTO restaurant;
	private UserDTO client;
	private AddressDTO deliveryAddress;
	@ApiModelProperty(example = "CRIADO")
	private OrderStatus status;
	private List<OrderItemDTO> itens;
}
