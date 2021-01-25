package com.rafaelsousa.algafood.jpa.restaurante;

import com.rafaelsousa.algafood.AlgafoodApiApplication;
import com.rafaelsousa.algafood.domain.model.Restaurante;
import com.rafaelsousa.algafood.domain.repository.RestauranteRepository;
import com.rafaelsousa.algafood.infrastructure.repository.RestauranteRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

import static com.rafaelsousa.algafood.utils.NumberFormatUtils.dataFormatada;
import static com.rafaelsousa.algafood.utils.NumberFormatUtils.valorMonetarioFormatado;

public class BuscaRestauranteMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepositoryImpl.class);

        List<Restaurante> restaurantes = restauranteRepository.listar();

        for (Restaurante r : restaurantes) {
            System.out.printf("%d - %s - %s - %s - %s - %s - %s\n"
                    , r.getId(), r.getNome(), valorMonetarioFormatado(r.getTaxaFrete()), r.getAtivo() ? "ATIVO" : "INATIVO", r.getAberto() ? "ABERTO" : "FECHADO"
                    , dataFormatada(r.getDataCadastro()), dataFormatada(r.getDataAtualizacao()));
        }
    }

}
