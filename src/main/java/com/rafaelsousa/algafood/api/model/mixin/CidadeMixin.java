package com.rafaelsousa.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rafaelsousa.algafood.domain.model.Estado;

public class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;

}
