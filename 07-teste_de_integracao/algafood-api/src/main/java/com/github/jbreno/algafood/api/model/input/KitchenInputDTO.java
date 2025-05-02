package com.github.jbreno.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenInputDTO {
	@NotBlank
	private String name;
}
