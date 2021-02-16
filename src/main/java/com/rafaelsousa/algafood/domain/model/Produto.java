package com.rafaelsousa.algafood.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private Boolean ativo = Boolean.TRUE;

    @ManyToOne
    private Restaurante restaurante;

}
