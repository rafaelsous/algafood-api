package com.rafaelsousa.algafood.domain.repository;

import com.rafaelsousa.algafood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> listar();
    Cidade salvar(Cidade cozinha);
    Cidade buscar(Long id);
    void remover(Long id);

}
