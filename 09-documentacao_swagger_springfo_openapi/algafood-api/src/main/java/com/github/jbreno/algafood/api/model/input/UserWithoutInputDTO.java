package com.github.jbreno.algafood.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithoutInputDTO {
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
}
