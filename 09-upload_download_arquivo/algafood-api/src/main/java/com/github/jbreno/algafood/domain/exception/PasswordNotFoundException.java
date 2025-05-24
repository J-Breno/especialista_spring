package com.github.jbreno.algafood.domain.exception;

public class PasswordNotFoundException extends EntityNotFoundException{
	private static final long serialVersionUID = 1L;

	public PasswordNotFoundException(String message) {
		super(message);
	}

	public PasswordNotFoundException(Long id) {
		this(String.format("Senha inexistente", id));
	}
}
