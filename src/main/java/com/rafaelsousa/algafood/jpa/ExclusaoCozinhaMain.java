package com.rafaelsousa.algafood.jpa;

import com.rafaelsousa.algafood.AlgafoodApiApplication;
import com.rafaelsousa.algafood.domain.model.Cozinha;
import com.rafaelsousa.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class ExclusaoCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        cozinhaRepository.remover(1L);

        List<Cozinha> cozinhas = cozinhaRepository.listar();
        cozinhas.forEach(c -> System.out.printf("%d - %s\n", c.getId(), c.getNome()));
    }

}
