package com.poc.crud.dao;

import com.poc.crud.model.Pedido;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PedidoDao implements Dao<Pedido, Long> {
    private final EntityManager em;

    @Override
    public void salvar(Pedido valor) {
        this.em.persist(valor);
    }

    @Override
    public void atualizar(Pedido valor) {
        this.em.merge(valor);
    }

    @Override
    public void remover(Pedido valor) {
        this.em.remove(valor);
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return this.em.find(Pedido.class, id);
    }

    @Override
    public List<Pedido> buscarTodos() {
        return this.em.
                createQuery("from Pedido", Pedido.class).
                getResultList();
    }
}
