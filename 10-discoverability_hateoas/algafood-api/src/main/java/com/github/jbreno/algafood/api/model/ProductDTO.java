package com.github.jbreno.algafood.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductDTO {
	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty(example = "PC")
	private String name;
	@ApiModelProperty(example = "pc gamer do mac")
	private String description;
	@ApiModelProperty(example = "12345.6")
	private BigDecimal price;
	@ApiModelProperty(example = "true")
	private Boolean active;
}
