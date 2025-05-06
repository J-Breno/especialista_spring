package com.github.jbreno.algafood.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.jbreno.algafood.api.model.view.RestaurantView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RestaurantDTO {
	@JsonView({RestaurantView.Resum.class, RestaurantView.NameOnly.class})
	@ApiModelProperty(example = "1", required = true)
	private Long id;
	@JsonView({RestaurantView.Resum.class, RestaurantView.NameOnly.class})
	@ApiModelProperty(example = "Thai Gourmet")
	private String name;
	@JsonView(RestaurantView.Resum.class)
	@ApiModelProperty(example = "20.12")
	private BigDecimal shippingFee;
	@JsonView(RestaurantView.Resum.class)
	private KitchenDTO kitchen;
	@ApiModelProperty(example = "True")
	private Boolean active;
	@ApiModelProperty(example = "aberto")
	private Boolean open;
	private AddressDTO address;
}
