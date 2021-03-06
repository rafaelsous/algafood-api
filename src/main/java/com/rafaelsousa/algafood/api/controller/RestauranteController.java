package com.rafaelsousa.algafood.api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafaelsousa.algafood.core.validation.ValidationException;
import com.rafaelsousa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.rafaelsousa.algafood.domain.exception.NegocioException;
import com.rafaelsousa.algafood.domain.model.Restaurante;
import com.rafaelsousa.algafood.domain.repository.RestauranteRepository;
import com.rafaelsousa.algafood.domain.service.CadastroRestauranteService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;
    private final CadastroRestauranteService cadastroRestaurante;
    private final SmartValidator validator;

    @Autowired
    public RestauranteController(RestauranteRepository restauranteRepository, CadastroRestauranteService cadastroRestaurante, SmartValidator validator) {
        this.restauranteRepository = restauranteRepository;
        this.cadastroRestaurante = cadastroRestaurante;
        this.validator = validator;
    }

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Restaurante buscar(@PathVariable Long id) {
        return cadastroRestaurante.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
        try {
            return cadastroRestaurante.salvar(restaurante);
        } catch (EntidadeNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Restaurante atualizar(@PathVariable Long id, @RequestBody @Valid Restaurante restaurante) {
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(id);
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "dataCadastro", "formasPagamento", "endereco", "produtos");

        try {
            return cadastroRestaurante.salvar(restauranteAtual);
        } catch (EntidadeNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroRestaurante.excluir(id);
    }

    @PatchMapping("/{id}")
    public Restaurante atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos, HttpServletRequest request) {
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(id);
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        merge(campos, restauranteAtual, serverHttpRequest);

        validate(restauranteAtual, "restaurante");

        return atualizar(id, restauranteAtual);
    }

    private void validate(Restaurante restaurante, String objecName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objecName);
        validator.validate(restaurante, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, ServletServerHttpRequest serverHttpRequest) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            dadosOrigem.forEach((nomeAtributo, valorAtributo) -> {
                // Retorna uma instância de um campo baseado no nome do atributo
                Field atributo = ReflectionUtils.findField(Restaurante.class, nomeAtributo);
                Objects.requireNonNull(atributo).setAccessible(true);

                Object novoValor = ReflectionUtils.getField(atributo, restauranteOrigem);

                ReflectionUtils.setField(atributo, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException ex) {
            Throwable rootCause = ExceptionUtils.getRootCause(ex);
            throw new HttpMessageNotReadableException(ex.getMessage(), rootCause, serverHttpRequest);
        }
    }
}
