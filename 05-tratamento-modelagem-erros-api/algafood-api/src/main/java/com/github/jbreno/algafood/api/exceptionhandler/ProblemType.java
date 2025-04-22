package com.github.jbreno.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	INCOMPREHENSIBLE_MESSAGE("/incomprehensible_message", "Mensagem incompreensível"),
	ENTITY_NOT_FOUND("/entity-not-found", "Entidade não encontrada"),
	ENTITY_IN_USE("/entity-in-use", "Entidade em uso"),
	ERROR_BUSINESS("/error-business", "Violação de regra de negócio");
	
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
}
