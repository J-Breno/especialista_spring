package com.github.jbreno.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenInputDTO {
	@NotBlank
	@ApiModelProperty(example = "Tailandesa")
	private String name;
}
