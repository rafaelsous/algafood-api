package com.rafaelsousa.algafood.api.controller;

import com.rafaelsousa.algafood.api.exceptionhandler.Problema;
import com.rafaelsousa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.rafaelsousa.algafood.domain.exception.EstadoNaoEncontradoException;
import com.rafaelsousa.algafood.domain.exception.NegocioException;
import com.rafaelsousa.algafood.domain.model.Cidade;
import com.rafaelsousa.algafood.domain.repository.CidadeRepository;
import com.rafaelsousa.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeRepository cidadeRepository;
    private final CadastroCidadeService cadastroCidade;

    @Autowired
    public CidadeController(CidadeRepository cidadeRepository, CadastroCidadeService cadastroCidade) {
        this.cidadeRepository = cidadeRepository;
        this.cadastroCidade = cadastroCidade;
    }

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cidade buscar(@PathVariable Long id) {
        return cadastroCidade.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade) {
        try {
            return cadastroCidade.salvar(cidade);
        } catch (EstadoNaoEncontradoException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        try {
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);

            BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            return cadastroCidade.salvar(cidadeAtual);
        } catch (EstadoNaoEncontradoException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroCidade.excluir(id);
    }
}
