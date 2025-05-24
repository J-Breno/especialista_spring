package com.github.jbreno.algafood.domain.exception;
public class PhotoProductNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public PhotoProductNotFoundException(String mensagem) {
        super(mensagem);
    }

    public PhotoProductNotFoundException(Long restaurantId, Long productId) {
        this(String.format("Não existe um cadastro de foto do produto com código %d para o restaurante de código %d",
        		productId, restaurantId));
    }
}