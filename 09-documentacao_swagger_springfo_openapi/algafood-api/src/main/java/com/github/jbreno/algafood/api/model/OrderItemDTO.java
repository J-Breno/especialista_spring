package com.github.jbreno.algafood.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemDTO {
	@ApiModelProperty(example = "1", required = true)
	private Long productId;
	@ApiModelProperty(example = "PC", required = true)
	private String productName;
	@ApiModelProperty(example = "10", required = true)
	private Integer quantity;
	@ApiModelProperty(example = "123.2", required = true)
	private BigDecimal unitPrice;
	@ApiModelProperty(example = "155.2", required = true)
	private BigDecimal totalPrice;
	@ApiModelProperty(example = "Observação de item pedido", required = true)
	private String observation;
}
