package com.poc.crud.dao;

import com.poc.crud.dto.ResumoCliente;
import com.poc.crud.dto.ResumoPedido;
import com.poc.crud.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

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
        return List.of();
    }


    public List<Produto> buscarTodos(int pagina, int qtd) {
        // usando o JPQL
        TypedQuery<Produto> query = em.createQuery("select p from Produto p", Produto.class);

        // PAGINAÇÃO
        query.setFirstResult(pagina * qtd);
        query.setMaxResults(qtd);

        return query.getResultList();

        /* sem JPQL
        return em
                .createQuery("select p from Produto p", Produto.class)
                .getResultList();

         */
    }


    // usando o JPQL
    public Produto buscarPorNome(String nome) {
        TypedQuery<Produto> query = em.createQuery("from Produto p where nome = :paramNome", Produto.class);
        query.setParameter("paramNome", "%" + nome + "%");
        return query.getSingleResult();
    }

    //selecionar so o que precisamos
    public List<String> buscarPorNomeProdutos() {
        TypedQuery<String> query = em.createQuery("select p.nome from Produto p", String.class);
        return query.getResultList();
    }

    // projetar direto para um dto
    public List<ResumoPedido> buscarResumoProduto() {
        TypedQuery<ResumoPedido> query = em.createQuery("select new com.poc.crud.dto.ResumoPedido(p.nome, p.valor) from Produto p", ResumoPedido.class);
        return query.getResultList();
    }

    // funçoes de agregações
    public List<ResumoCliente> buscarResumoCliente() {
        TypedQuery<ResumoCliente> query = em.createQuery("select new com.poc.crud.dto.ResumoCliente(p.cpf, sum(p.valor)) from Pedido p", ResumoCliente.class);
        return query.getResultList();
    }
}
