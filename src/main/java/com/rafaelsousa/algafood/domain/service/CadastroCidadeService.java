package com.rafaelsousa.algafood.domain.service;

import com.rafaelsousa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.rafaelsousa.algafood.domain.model.Cidade;
import com.rafaelsousa.algafood.domain.model.Estado;
import com.rafaelsousa.algafood.domain.repository.CidadeRepository;
import com.rafaelsousa.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    public static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cidade com o código %d.";
    public static final String MSG_ESTADO_NAO_ENCONTRADO = "Estado com o código %d não existe";

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    private final CadastroEstadoService cadastroEstado;

    @Autowired
    public CadastroCidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository, CadastroEstadoService cadastroEstado) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
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
            throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, id));
        }
    }

    public Cidade buscarOuFalhar(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, id)));
    }
}
