package com.github.jbreno.algafood.api.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;


public class OrderItemInputDTO {

	@NotNull
	@ApiModelProperty(example = "1", required = true)
	private Long productId;
	@NotNull
	@PositiveOrZero
	@ApiModelProperty(example = "2", required = true)
	private Integer quantity;
	
	@ApiModelProperty(example = "Menos picante, por favor", required = true)
	private String observation;
	
	public Long getProductId() {
		return productId;
	}



	public void setProductId(Long productId) {
		this.productId = productId;
	}



	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	public String getObservation() {
		return observation;
	}



	public void setObservation(String observation) {
		this.observation = observation;
	}

	public OrderItemInputDTO() {
    }
}
