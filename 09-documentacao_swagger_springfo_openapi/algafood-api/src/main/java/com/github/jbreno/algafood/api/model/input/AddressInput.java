package com.github.jbreno.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInput {
	
	@NotBlank
	@ApiModelProperty(example = "38400-000", required = true)
	private String cep;
	
	@NotBlank
	@ApiModelProperty(example = "Rua Floriano Peixoto", required = true)
	private String street;
	
	@NotBlank
	@ApiModelProperty(example = "1500", required = true)
	private String number;
	
	@ApiModelProperty(example = "Apto 901", required = true)
	private String complement;
	
	@ApiModelProperty(example = "Centro", required = true)
	@NotBlank
	private String neighborhood;
	
	@Valid
	@NotNull
	private CityIdInput city;
}
