package com.poc.crud.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Cliente extends Pessoa {
    @Column(nullable = false, unique = true)
    private String cpf;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Set<Pedido> pedidos = new HashSet<>();
}
