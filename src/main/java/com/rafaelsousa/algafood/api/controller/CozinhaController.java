package com.rafaelsousa.algafood.api.controller;

import com.rafaelsousa.algafood.api.controller.model.CozinhasXmlWrapper;
import com.rafaelsousa.algafood.domain.model.Cozinha;
import com.rafaelsousa.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaRepository.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Cozinha cozinha = cozinhaRepository.buscar(id);

        if (Objects.nonNull(cozinha)) {
            return ResponseEntity.ok(cozinha);
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaRepository.salvar(cozinha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaEncontrada = cozinhaRepository.buscar(id);

        if (Objects.nonNull(cozinhaEncontrada)) {
            BeanUtils.copyProperties(cozinha, cozinhaEncontrada, "id");
            cozinhaEncontrada = cozinhaRepository.salvar(cozinhaEncontrada);

            return ResponseEntity.ok(cozinhaEncontrada);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
        try {
            cozinhaRepository.remover(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            if (ex instanceof DataIntegrityViolationException) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

            return ResponseEntity.notFound().build();
        }
    }
}
