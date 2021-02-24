package com.rafaelsousa.algafood.api.controller;

import com.rafaelsousa.algafood.domain.model.Estado;
import com.rafaelsousa.algafood.domain.repository.EstadoRepository;
import com.rafaelsousa.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoRepository estadoRepository;
    private final CadastroEstadoService cadastroEstado;

    @Autowired
    public EstadoController(EstadoRepository estadoRepository, CadastroEstadoService cadastroEstado) {
        this.estadoRepository = estadoRepository;
        this.cadastroEstado = cadastroEstado;
    }

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Estado buscar(@PathVariable Long id) {
        return cadastroEstado.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado) {
        return cadastroEstado.salvar(estado);
    }

    @PutMapping("/{id}")
    public Estado atualizar(@PathVariable Long id, @RequestBody Estado estado) {
        Estado estadoAtual = cadastroEstado.buscarOuFalhar(id);
        BeanUtils.copyProperties(estado, estadoAtual, "id");

        return cadastroEstado.salvar(estadoAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroEstado.excluir(id);
    }
}
