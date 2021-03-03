package com.rafaelsousa.algafood.domain.exception;

public class EntidadeEmUsoException extends NegocioException {
    public EntidadeEmUsoException(String message) {
        super(message);
    }

    public EntidadeEmUsoException(String message, Throwable cause) {
        super(message, cause);
    }
}
