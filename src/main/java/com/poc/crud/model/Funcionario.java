package com.poc.crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Funcionario extends Pessoa {
    @Column(nullable = false, unique = true)
    private String matricula;
    @Column(nullable = false)
    private String cargo;
}
