package com.github.jbreno.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupInputDTO {
	@NotBlank
	private String name;
}
