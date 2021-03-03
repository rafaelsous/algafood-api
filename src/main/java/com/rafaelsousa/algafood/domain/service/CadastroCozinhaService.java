package com.rafaelsousa.algafood.domain.service;

import com.rafaelsousa.algafood.domain.exception.EntidadeEmUsoException;
import com.rafaelsousa.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.rafaelsousa.algafood.domain.model.Cozinha;
import com.rafaelsousa.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    public static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso.";

    private final CozinhaRepository cozinhaRepository;

    @Autowired
    public CadastroCozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, id));
        } catch (EmptyResultDataAccessException ex) {
            throw new CozinhaNaoEncontradaException(id);
        }
    }

    public Cozinha buscarOuFalhar(Long id) {
        return cozinhaRepository.findById(id).orElseThrow(
                () -> new CozinhaNaoEncontradaException(id));
    }
}
