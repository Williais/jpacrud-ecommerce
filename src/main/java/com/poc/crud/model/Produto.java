package com.poc.crud.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Integer quantidade;
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public BigDecimal getTotal() {
        if (this.valor == null || this.quantidade == null) {
            return BigDecimal.ZERO;
        }
        return this.valor.multiply(BigDecimal.valueOf(this.quantidade));
    }
}
