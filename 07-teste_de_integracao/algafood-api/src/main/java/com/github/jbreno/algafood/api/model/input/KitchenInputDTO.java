package com.github.jbreno.algafood.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenInputDTO {
	@NotNull
	private Long id;
	@NotBlank
	private String name;
}
