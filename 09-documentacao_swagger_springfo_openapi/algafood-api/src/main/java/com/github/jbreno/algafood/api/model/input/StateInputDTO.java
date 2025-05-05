package com.github.jbreno.algafood.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateInputDTO {
	@NotNull
	@ApiModelProperty(example = "1")
	private Long id;
	@NotBlank
	@ApiModelProperty(example = "Ceara")
	private String name;
}
