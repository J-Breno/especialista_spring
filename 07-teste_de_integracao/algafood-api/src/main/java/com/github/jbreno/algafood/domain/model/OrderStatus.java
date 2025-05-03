package com.github.jbreno.algafood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {

	CREATING("Criado"),
	CONFIRMED("Confirmado", CREATING),
	DELIVERED("Entregue", CONFIRMED),
	CANCELED("Cancelado", CREATING);
	
	private String description;
	private List<OrderStatus> previousStatuses;
	
	OrderStatus(String description, OrderStatus... previousStatuses) {
		this.description = description;
		this.previousStatuses = Arrays.asList(previousStatuses);
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public boolean cannnotChangeTo(OrderStatus newStatus) {
		return !newStatus.previousStatuses.contains(this);
	}

}
