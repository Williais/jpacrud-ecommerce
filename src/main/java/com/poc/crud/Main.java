package com.poc.crud;

import com.poc.crud.dao.ClienteDao;
import com.poc.crud.dao.PedidoDao;
import com.poc.crud.dao.ProdutoDao;
import com.poc.crud.dto.ResumoCliente;
import com.poc.crud.dto.ResumoPedido;
import com.poc.crud.model.Cliente;
import com.poc.crud.model.Endereco;
import com.poc.crud.model.Pedido;
import com.poc.crud.model.Produto;
import jakarta.persistence.EntityManager;
import com.poc.crud.util.JpaUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try(EntityManager em = JpaUtil.getEntityManager()){
            em.getTransaction().begin();
            ClienteDao clienteDao = new ClienteDao(em);
            Cliente cliente = new Cliente();
            cliente.setNome("Will");
            cliente.setCpf("12345678912");
            cliente.setEndereco(new Endereco(null, "rua teste", "44"));
            clienteDao.salvar(cliente);
            em.getTransaction().commit();

            ProdutoDao produtoDao = new ProdutoDao(em);
            Produto p = new Produto();
            p.setDescricao("descricao");
            p.setQuantidade(20);
            p.setNome("Banana");
            p.setValor(new BigDecimal("5.40"));
            em.getTransaction().begin();
            produtoDao.salvar(p);
            em.getTransaction().commit();


            Produto p2 = new Produto();
            p2.setDescricao("descricao");
            p2.setQuantidade(10);
            p2.setNome("Maçã");
            p2.setValor(new BigDecimal("6.20"));
            em.getTransaction().begin();
            produtoDao.salvar(p2);
            em.getTransaction().commit();

            PedidoDao pedidoDao = new PedidoDao(em);
            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.adicionaProduto(p);
            pedido.adicionaProduto(p2);
            em.getTransaction().begin();
            pedidoDao.salvar(pedido);

            Pedido pedido2 = pedidoDao.buscarPorId(pedido.getId());
            System.out.println(pedido2.getId());
            System.out.println(pedido2.getCliente().getNome());
            System.out.println(pedido2.getTotal());
            System.out.println(pedido2.getProdutos());

            Produto banana = produtoDao.buscarPorNome("Banana");
            System.out.println(banana.getNome());

            produtoDao.buscarTodos(2, 10);

            List<String> nomesDosProdutos = produtoDao.buscarPorNomeProdutos();
            nomesDosProdutos.forEach(System.out::println);

            List<ResumoPedido> resumoPedido = produtoDao.buscarResumoProduto();
            resumoPedido.forEach(System.out::println);

            List<ResumoCliente> resumoClientesPedido = produtoDao.buscarResumoCliente();
            resumoClientesPedido.forEach(System.out::println);

            em.getTransaction().commit();

        }
    }
}
