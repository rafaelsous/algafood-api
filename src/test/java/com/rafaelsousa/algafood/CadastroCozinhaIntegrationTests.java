package com.rafaelsousa.algafood;

import com.rafaelsousa.algafood.domain.model.Cozinha;
import com.rafaelsousa.algafood.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CadastroCozinhaIntegrationTests {

    private final CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    public CadastroCozinhaIntegrationTests(CadastroCozinhaService cadastroCozinhaService) {
        this.cadastroCozinhaService = cadastroCozinhaService;
    }

    @Test
    public void testarCadastroCozinhaComSucesso() {
        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        // ação
        novaCozinha = cadastroCozinhaService.salvar(novaCozinha);

        // validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test()
    public void testarCadastroCozinhaSemNome() {
        // validação
        assertThrows(ConstraintViolationException.class, () -> {
            // cenário
            Cozinha novaCozinha = new Cozinha();

            // ação
            cadastroCozinhaService.salvar(novaCozinha);
        });
    }

}
