package com.github.jbreno.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)//, reason = "Entidade não encontrada")
public class EntityNotFoundException extends ResponseStatusException{
	private static final long serialVersionUID = 1L;
	
	
	
	public EntityNotFoundException(HttpStatus status, String reason) {
		super(status, reason);
	}



	public EntityNotFoundException(String message) {
		this(HttpStatus.NOT_FOUND, message);
	}

}
