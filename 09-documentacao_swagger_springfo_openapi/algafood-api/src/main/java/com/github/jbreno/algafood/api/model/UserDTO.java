package com.github.jbreno.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
	@ApiModelProperty(example = "1", required = true)
	private Long id;
	@ApiModelProperty(example = "João", required = true)
	private String name;
	@ApiModelProperty(example = "joao@gmail.com", required = true)
	private String email;
}
