package com.github.jbreno.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.jbreno.algafood.domain.model.State;

public class CityMixin {
	
	@JsonIgnoreProperties(value = "name", allowGetters = true)
	private State state;
}
