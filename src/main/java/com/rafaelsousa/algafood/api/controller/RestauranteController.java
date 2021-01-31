package com.rafaelsousa.algafood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafaelsousa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.rafaelsousa.algafood.domain.model.Restaurante;
import com.rafaelsousa.algafood.domain.repository.RestauranteRepository;
import com.rafaelsousa.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;

    private final CadastroRestauranteService cadastroRestaurante;

    @Autowired
    public RestauranteController(RestauranteRepository restauranteRepository, CadastroRestauranteService cadastroRestaurante) {
        this.restauranteRepository = restauranteRepository;
        this.cadastroRestaurante = cadastroRestaurante;
    }

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Restaurante restaurante = restauranteRepository.buscar(id);

        if (Objects.nonNull(restaurante)) {
            return ResponseEntity.ok(restaurante);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            Restaurante novoRestaurante = cadastroRestaurante.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(novoRestaurante);
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante restauranteAtual = restauranteRepository.buscar(id);

        if (Objects.nonNull(restauranteAtual)) {
            try {
                BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "dataCadastro");
                Restaurante restauranteAtualizado = cadastroRestaurante.salvar(restauranteAtual);
                return ResponseEntity.ok(restauranteAtualizado);

            } catch (EntidadeNaoEncontradaException ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            cadastroRestaurante.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        Restaurante restauranteAtual = restauranteRepository.buscar(id);

        if (Objects.isNull(restauranteAtual)) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual);

        return atualizar(id, restauranteAtual);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomeAtributo, valorAtributo) -> {
            // Retorna uma instância de um campo baseado no nome do atributo
            Field atributo = ReflectionUtils.findField(Restaurante.class, nomeAtributo);
            Objects.requireNonNull(atributo).setAccessible(true);

            Object novoValor = ReflectionUtils.getField(atributo, restauranteOrigem);

            System.out.println(nomeAtributo + " = " + valorAtributo + " = " + novoValor);

            ReflectionUtils.setField(atributo, restauranteDestino, novoValor);
        });
    }
}
