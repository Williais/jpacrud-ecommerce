package com.poc.crud.dao;

import com.poc.crud.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class ProdutoDao implements Dao<Produto, Long>{
    private EntityManager em;
    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public void salvar(Produto valor) {
        em.persist(valor);
    }

    @Override
    public void atualizar(Produto valor) {
        em.merge(valor);
    }

    @Override
    public void remover(Produto valor) {
        em.remove(valor);
    }

    @Override
    public Produto buscarPorId(Long id) {
       return em.find(Produto.class, id);
    }

    @Override
    public List<Produto> buscarTodos() {

        return em
                .createQuery("select p from Produto p", Produto.class)
                .getResultList();
    }
}
