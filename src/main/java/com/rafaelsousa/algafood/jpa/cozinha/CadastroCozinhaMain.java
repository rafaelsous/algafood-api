package com.rafaelsousa.algafood.jpa.cozinha;

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

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
        Cozinha japonesa  = new Cozinha("Japonesa");
        Cozinha brasileira = new Cozinha("Brasileira");
        Cozinha c1 = cozinhaRepository.salvar(japonesa);
        Cozinha c2 = cozinhaRepository.salvar(brasileira);

        Arrays.asList(c1, c2).forEach(cozinha -> System.out.printf("%d - %s\n", cozinha.getId(), cozinha.getNome()));
    }

}
