package com.rafaelsousa.algafood.domain.service;

import com.rafaelsousa.algafood.domain.exception.CidadeNaoEncontradaException;
import com.rafaelsousa.algafood.domain.model.Cidade;
import com.rafaelsousa.algafood.domain.model.Estado;
import com.rafaelsousa.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    private final CidadeRepository cidadeRepository;

    private final CadastroEstadoService cadastroEstado;

    @Autowired
    public CadastroCidadeService(CidadeRepository cidadeRepository, CadastroEstadoService cadastroEstado) {
        this.cidadeRepository = cidadeRepository;
        this.cadastroEstado = cadastroEstado;
    }

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void excluir(Long id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new CidadeNaoEncontradaException(id);
        }
    }

    public Cidade buscarOuFalhar(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id));
    }
}
