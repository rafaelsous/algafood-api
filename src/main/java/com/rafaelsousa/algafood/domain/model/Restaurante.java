package com.rafaelsousa.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@DynamicUpdate
@Entity
public class Restaurante {

//    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

//    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    private Boolean ativo;

    private Boolean aberto;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

//    @JsonIgnore
    @JsonIgnoreProperties("hibernateLazyInitializer")
    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Cozinha cozinha;

//    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento;

    @JsonIgnore
    @Embedded
    private Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    public Restaurante(String nome, BigDecimal taxaFrete, Boolean ativo, Boolean aberto) {
        this.nome = nome;
        this.taxaFrete = taxaFrete;
        this.ativo = ativo;
        this.aberto = aberto;
    }
}
