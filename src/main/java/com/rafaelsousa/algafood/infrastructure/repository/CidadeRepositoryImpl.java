package com.rafaelsousa.algafood.infrastructure.repository;

import com.rafaelsousa.algafood.domain.model.Cidade;
import com.rafaelsousa.algafood.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> listar() {
        return manager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Transactional
    @Override
    public Cidade salvar(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    public Cidade buscar(Long id) {
        return manager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Cidade cidade = buscar(id);

        if (Objects.isNull(cidade)) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(cidade);
    }
}
