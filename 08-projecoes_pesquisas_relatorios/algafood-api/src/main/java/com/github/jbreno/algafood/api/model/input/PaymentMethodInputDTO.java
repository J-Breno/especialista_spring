package com.github.jbreno.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodInputDTO {
	@NotBlank
	private String description;
}
