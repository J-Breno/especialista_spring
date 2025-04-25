package com.github.jbreno.algafood.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jbreno.algafood.domain.model.Restaurant;

public class ProductMixin {
	@JsonIgnore
	private List<Restaurant> restaurants = new ArrayList<>();
}
