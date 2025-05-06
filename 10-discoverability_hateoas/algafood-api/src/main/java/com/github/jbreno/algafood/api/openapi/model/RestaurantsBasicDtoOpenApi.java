package com.github.jbreno.algafood.api.openapi.model;

import java.math.BigDecimal;

import com.github.jbreno.algafood.api.model.KitchenDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("RestauranteBasicoDto")
@Getter
@Setter
public class RestaurantsBasicDtoOpenApi {
	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty(example = "Thai Gourmet")
	private String name;
	@ApiModelProperty(example = "12.00")
	private BigDecimal shippingFee;
	private KitchenDTO kitchen;
}
