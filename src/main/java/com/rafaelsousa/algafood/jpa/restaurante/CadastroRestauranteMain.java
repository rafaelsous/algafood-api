package com.rafaelsousa.algafood.jpa.restaurante;

import com.rafaelsousa.algafood.AlgafoodApiApplication;
import com.rafaelsousa.algafood.domain.model.Restaurante;
import com.rafaelsousa.algafood.domain.repository.RestauranteRepository;
import com.rafaelsousa.algafood.infrastructure.repository.RestauranteRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.rafaelsousa.algafood.utils.NumberFormatUtils.dataFormatada;
import static com.rafaelsousa.algafood.utils.NumberFormatUtils.valorMonetarioFormatado;

public class CadastroRestauranteMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepositoryImpl.class);

        Restaurante bonnaBoca = new Restaurante("Bonna Boca", BigDecimal.valueOf(15.99), Boolean.TRUE, Boolean.TRUE);
        Restaurante mammaMia = new Restaurante("Mamma Mia", BigDecimal.valueOf(11.29), Boolean.TRUE, Boolean.FALSE);
        Restaurante r1 = restauranteRepository.salvar(bonnaBoca);
        Restaurante r2 = restauranteRepository.salvar(mammaMia);

        Arrays.asList(r1, r2).forEach(r -> System.out.printf("%d - %s - %s - %s - %s - %s - %s - %s\n"
                , r.getId(), r.getNome(), valorMonetarioFormatado(r.getTaxaFrete()), r.getAtivo() ? "ATIVO" : "INATIVO", r.getAberto() ? "ABERTO" : "FECHADO"
                , dataFormatada(r.getDataCadastro()), dataFormatada(r.getDataAtualizacao()), r.getCozinha().getNome()));
    }

}
