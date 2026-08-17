package com.poc.crud.dao;

import com.poc.crud.model.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteDao implements Dao<Cliente, Long> {
    private final EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void salvar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public void atualizar(Cliente cliente) {
        this.em.merge(cliente);
    }

    @Override
    public void remover(Cliente valor) {
        this.em.remove(valor);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return this.em.find(Cliente.class, id);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return this.em.
                createQuery("from Cliente", Cliente.class).
                getResultList();
    }
}
