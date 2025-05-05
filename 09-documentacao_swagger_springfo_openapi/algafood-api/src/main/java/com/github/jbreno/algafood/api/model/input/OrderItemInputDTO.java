package com.github.jbreno.algafood.api.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


public class OrderItemInputDTO {

	@NotNull
	private Long productId;
	@NotNull
	@PositiveOrZero
	private Integer quantity;
	
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
