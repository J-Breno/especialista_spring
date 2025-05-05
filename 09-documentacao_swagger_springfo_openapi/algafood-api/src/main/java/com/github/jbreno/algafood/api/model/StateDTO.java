package com.github.jbreno.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StateDTO {
	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty(example = "Ceara")
	private String name;
}
