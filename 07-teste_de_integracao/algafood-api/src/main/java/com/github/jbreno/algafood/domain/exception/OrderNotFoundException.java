package com.github.jbreno.algafood.domain.exception;

public class OrderNotFoundException extends EntityNotFoundException{
	private static final long serialVersionUID = 1L;



	public OrderNotFoundException(String code) {
		super(String.format("Não existe um cadastro de pedido com código %s", code));
	}
}
