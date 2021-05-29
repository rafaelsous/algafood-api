package com.rafaelsousa.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rafaelsousa.algafood.domain.model.Cozinha;
import com.rafaelsousa.algafood.domain.model.Endereco;
import com.rafaelsousa.algafood.domain.model.FormaPagamento;
import com.rafaelsousa.algafood.domain.model.Produto;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "nome" }, allowGetters = true)
    private @Valid Cozinha cozinha;

    @JsonIgnore
    private List<FormaPagamento> formasPagamento;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

}
