package com.github.jbreno.algafood.domain.exception;

public class EntityInUseException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EntityInUseException(String message) {
		super(message);
	}
}
