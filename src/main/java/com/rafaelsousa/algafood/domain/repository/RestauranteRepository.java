package com.rafaelsousa.algafood.domain.repository;

import com.rafaelsousa.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> listar();
    Restaurante salvar(Restaurante restaurante);
    Restaurante buscar(Long id);
    void remover(Long id);

}
