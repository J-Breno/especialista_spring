package com.github.jbreno.algafood.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithoutInputDTO {
	@NotBlank
	@ApiModelProperty(example = "joão", required = true)
	private String name;
	@NotBlank
	@Email
	@ApiModelProperty(example = "joao@gmail.com", required = true)
	private String email;
}
