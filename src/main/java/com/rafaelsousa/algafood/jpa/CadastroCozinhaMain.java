package com.rafaelsousa.algafood.jpa;

import com.rafaelsousa.algafood.AlgafoodApiApplication;
import com.rafaelsousa.algafood.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

public class CadastroCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
        Cozinha japonesa  = new Cozinha("Japonesa");
        Cozinha brasileira = new Cozinha("Brasileira");
        Cozinha c1 = cadastroCozinha.adicionar(japonesa);
        Cozinha c2 = cadastroCozinha.adicionar(brasileira);

//        List<Cozinha> cozinhas = cadastroCozinha.listar();
        Arrays.asList(c1, c2).forEach(cozinha -> System.out.printf("%d - %s\n", cozinha.getId(), cozinha.getNome()));
    }

}
