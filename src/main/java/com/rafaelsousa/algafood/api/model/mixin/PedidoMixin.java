package com.rafaelsousa.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rafaelsousa.algafood.domain.model.Endereco;

public class PedidoMixin {

    @JsonIgnore
    private Endereco enderecoEntrega;

}
