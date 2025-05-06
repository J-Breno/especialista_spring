package com.github.jbreno.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
	@ApiModelProperty(example = "38400-000", required = true)
	private String cep;
	@ApiModelProperty(example = "Rua Floriano Peixoto", required = true)
	private String street;
	@ApiModelProperty(example = "1500", required = true)
	private String number;
	@ApiModelProperty(example = "Apto 901", required = true)
	private String complement;
	@ApiModelProperty(example = "Centro", required = true)
	private String neighborhood;
	
	private CityResumDTO city;
}
