package com.github.jbreno.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityResumDTO {
	@ApiModelProperty(example = "1", required = true)
	private Long id;
	@ApiModelProperty(example = "Fortaleza", required = true)
	private String name;
	@ApiModelProperty(example = "Ceara", required = true)
	private String nameState;
}
