package com.github.jbreno.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupInputDTO {
	@ApiModelProperty(example = "Gerente", required = true)
	@NotBlank
	private String name;
}
