package com.rafaelsousa.algafood.api.controller;

import com.rafaelsousa.algafood.domain.exception.EntidadeEmUsoException;
import com.rafaelsousa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.rafaelsousa.algafood.domain.model.Cozinha;
import com.rafaelsousa.algafood.domain.repository.CozinhaRepository;
import com.rafaelsousa.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaRepository cozinhaRepository;

    private final CadastroCozinhaService cadastroCozinha;

    @Autowired
    public CozinhaController(CozinhaRepository cozinhaRepository, CadastroCozinhaService cadastroCozinha) {
        this.cozinhaRepository = cozinhaRepository;
        this.cadastroCozinha = cadastroCozinha;
    }

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

        if (cozinha.isPresent()) {
            return ResponseEntity.ok(cozinha.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        Cozinha novaCozinha = cadastroCozinha.salvar(cozinha);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        Optional<Cozinha> cozinhaEncontrada = cozinhaRepository.findById(id);

        if (cozinhaEncontrada.isPresent()) {
            BeanUtils.copyProperties(cozinha, cozinhaEncontrada.get(), "id");
            Cozinha cozinhaAtualizada = cadastroCozinha.salvar(cozinhaEncontrada.get());

            return ResponseEntity.ok(cozinhaAtualizada);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
        try {
            cadastroCozinha.excluir(id);
            return ResponseEntity.noContent().build();

        } catch (EntidadeEmUsoException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
