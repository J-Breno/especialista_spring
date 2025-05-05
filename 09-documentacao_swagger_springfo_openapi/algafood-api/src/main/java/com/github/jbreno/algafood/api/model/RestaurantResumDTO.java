package com.github.jbreno.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RestaurantResumDTO {
	@ApiModelProperty(example = "1", required = true)
	private Long id;
	@ApiModelProperty(example = "Tailandia", required = true)
	private String name;
}
