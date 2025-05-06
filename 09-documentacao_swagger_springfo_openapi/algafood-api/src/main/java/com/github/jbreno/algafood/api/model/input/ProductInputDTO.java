package com.github.jbreno.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInputDTO {
	@NotBlank
	@ApiModelProperty(example = "PC", required = true)
	private String name;
	@NotBlank
	@ApiModelProperty(example = "PC gamer do MAC")
	private String description;
	@NotNull
	@PositiveOrZero
	@ApiModelProperty(example = "12345.6")
	private BigDecimal price;
	@NotNull
	@ApiModelProperty(example = "true")
	private Boolean active;
}