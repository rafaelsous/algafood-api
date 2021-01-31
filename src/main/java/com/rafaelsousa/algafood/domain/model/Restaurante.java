package com.rafaelsousa.algafood.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
//@Getter @Setter
@NoArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

//    @Column(name = "data_cadastro")
    @CreationTimestamp
    private LocalDateTime dataCadastro;

//    @Column(name = "data_atualizacao")
    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    @ManyToOne
    private Cozinha cozinha;

    public Restaurante(String nome, BigDecimal taxaFrete, Boolean ativo, Boolean aberto) {
        this.nome = nome;
        this.taxaFrete = taxaFrete;
        this.ativo = ativo;
        this.aberto = aberto;
    }
}
