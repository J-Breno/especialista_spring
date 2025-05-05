package com.github.jbreno.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.jbreno.algafood.api.model.view.RestaurantView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenDTO {
	@JsonView(RestaurantView.Resum.class)
	private Long id;
	@JsonView(RestaurantView.Resum.class)
	private String name;
}
