package com.rafaelsousa.algafood.domain.repository;

import com.rafaelsousa.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    @Query(value = "FROM Restaurante res WHERE nome LIKE %:nome% AND res.cozinha.id = :cozinhaId")
    List<Restaurante> buscarPorNomeECozinhaId(String nome, Long cozinhaId);

//    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

}
