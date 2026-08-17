package com.poc.crud.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal total = BigDecimal.ZERO;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private Set<Produto> produtos = new HashSet<>();

    public void adicionaProduto(Produto produto) {
        this.produtos.add(produto);
        this.total = this.total.add(produto.getValor().multiply(BigDecimal.valueOf(produto.getQuantidade())));
    }

    public void removeProduto(Produto produto) {
        this.produtos.remove(produto);
        this.total = this.total.subtract(produto.getValor());
    }
}
