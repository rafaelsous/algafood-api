package com.rafaelsousa.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
    public RestauranteNaoEncontradoException(String message) {
        super(message);
    }

    public RestauranteNaoEncontradoException(Long restauranteId) {
        this(String.format("Não existe um cadastro de restaurante com o código %d.", restauranteId));
    }
}
