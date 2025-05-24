package com.github.jbreno.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
	private String cep;
	private String street;
	private String number;
	private String complement;
	private String neighborhood;
	private CityResumDTO city;
}
