package com.github.jbreno.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Setter
@Getter
public class CityDTO {
	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty( example = "Fortaleza")
	private String name;
	private StateDTO state;
}
