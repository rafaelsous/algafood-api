package com.rafaelsousa.algafood.domain.model;

import lombok.Getter;

public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    StatusPedido(String label) {
        this.label = label;
    }

    @Getter
    private String label;
}
