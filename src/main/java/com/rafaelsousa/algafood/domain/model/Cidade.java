package com.rafaelsousa.algafood.domain.model;

import com.rafaelsousa.algafood.Groups;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

@Data
@NoArgsConstructor
@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @ConvertGroup(to = Groups.EstadoId.class)
    @ManyToOne
    @JoinColumn(nullable = false)
    private @Valid Estado estado;

}
