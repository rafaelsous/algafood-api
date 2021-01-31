package com.rafaelsousa.algafood.domain.repository;

import com.rafaelsousa.algafood.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> listar();
    List<Cozinha> consultarPorNome(String nome);
    Cozinha buscar(Long id);

    Cozinha salvar(Cozinha cozinha);
    void remover(Long id);

}
