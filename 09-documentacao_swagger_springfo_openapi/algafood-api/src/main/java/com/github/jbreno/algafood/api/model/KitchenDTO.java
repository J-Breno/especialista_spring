package com.github.jbreno.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.jbreno.algafood.api.model.view.RestaurantView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KitchenDTO {
	@JsonView(RestaurantView.Resum.class)
	@ApiModelProperty(example = "1")
	private Long id;
	@JsonView(RestaurantView.Resum.class)
	@ApiModelProperty(example = "Tailandesa")
	private String name;
}
