package com.github.jbreno.algafood.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.jbreno.algafood.api.model.view.RestaurantView;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RestaurantDTO {
	@JsonView({RestaurantView.Resum.class, RestaurantView.NameOnly.class})
	private Long id;
	@JsonView({RestaurantView.Resum.class, RestaurantView.NameOnly.class})
	private String name;
	@JsonView(RestaurantView.Resum.class)
	private BigDecimal shippingFee;
	@JsonView(RestaurantView.Resum.class)
	private KitchenDTO kitchen;
	private Boolean active;
	private Boolean open;
	private AddressDTO address;
}
