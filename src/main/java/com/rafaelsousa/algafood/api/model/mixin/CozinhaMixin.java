package com.rafaelsousa.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rafaelsousa.algafood.domain.model.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes = new ArrayList<>();

}
