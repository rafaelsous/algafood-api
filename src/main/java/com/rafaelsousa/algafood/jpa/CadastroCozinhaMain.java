package com.rafaelsousa.algafood.jpa;

import com.rafaelsousa.algafood.AlgafoodApiApplication;
import com.rafaelsousa.algafood.domain.model.Cozinha;
import com.rafaelsousa.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

public class CadastroCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);
        Cozinha japonesa  = new Cozinha("Japonesa");
        Cozinha brasileira = new Cozinha("Brasileira");
        Cozinha c1 = cadastroCozinha.salvar(japonesa);
        Cozinha c2 = cadastroCozinha.salvar(brasileira);

        System.out.println(c1.equals(c1));

        Arrays.asList(c1, c2).forEach(cozinha -> System.out.printf("%d - %s\n", cozinha.getId(), cozinha.getNome()));
    }

}
