package com.github.jbreno.algafood.domain.model;

public enum OrderStatus {

	CREATING("Criado"),
	CONFIRMED("Confirmado"),
	DELIVERED("Entregue"),
	CANCELED("Cancelado");
	
	private String description;
	
	OrderStatus(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
