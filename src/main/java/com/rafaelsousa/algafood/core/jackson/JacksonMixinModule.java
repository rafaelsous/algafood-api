package com.rafaelsousa.algafood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.rafaelsousa.algafood.api.model.mixin.CidadeMixin;
import com.rafaelsousa.algafood.api.model.mixin.CozinhaMixin;
import com.rafaelsousa.algafood.api.model.mixin.PedidoMixin;
import com.rafaelsousa.algafood.api.model.mixin.RestauranteMixin;
import com.rafaelsousa.algafood.domain.model.Cidade;
import com.rafaelsousa.algafood.domain.model.Cozinha;
import com.rafaelsousa.algafood.domain.model.Pedido;
import com.rafaelsousa.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
         setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
         setMixInAnnotation(Cidade.class, CidadeMixin.class);
         setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
         setMixInAnnotation(Pedido.class, PedidoMixin.class);
    }
}
