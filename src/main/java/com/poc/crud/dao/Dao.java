package com.poc.crud.dao;

import java.util.List;

public interface Dao<K, V> {
    void salvar(K valor);
    void atualizar(K valor);
    void remover(K valor);
    K buscarPorId(V id);
    List<K> buscarTodos();
}
