package com.github.jbreno.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityInputDTO {
	@NotBlank
	private String name;
	@Valid
	@NotNull
	private StateInputDTO state;
}
