package com.github.jbreno.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StateDTO {
	@ApiModelProperty(example = "1", required = true)
	private Long id;
	@ApiModelProperty(example = "Ceara", required = true)
	private String name;
}
