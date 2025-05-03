package com.github.jbreno.algafood.domain.exception;

public class PermissionNotFoundException extends EntityNotFoundException{
	private static final long serialVersionUID = 1L;

	public PermissionNotFoundException(String message) {
		super(message);
	}

	public PermissionNotFoundException(Long id) {
		this(String.format("Não existe um cadastro de cidade com código %d", id));
	}
}
