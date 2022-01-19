package com.rafaelsousa.algafood.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class RestauranteResponseDTO {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaResponseDTO cozinha;
}
