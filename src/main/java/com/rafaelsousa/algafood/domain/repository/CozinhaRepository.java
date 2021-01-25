package com.rafaelsousa.algafood.domain.repository;

import com.rafaelsousa.algafood.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> listar();
    Cozinha salvar(Cozinha cozinha);
    Cozinha buscar(Long id);
    void remover(Long id);

}
