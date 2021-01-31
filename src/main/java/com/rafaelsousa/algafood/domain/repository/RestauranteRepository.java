package com.rafaelsousa.algafood.domain.repository;

import com.rafaelsousa.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

//    @Query(value = "FROM Restaurante res WHERE nome LIKE %:nome% AND res.cozinha.id = :cozinhaId")
    List<Restaurante> buscarPorNomeECozinhaId(String nome, Long cozinhaId);

//    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

}
