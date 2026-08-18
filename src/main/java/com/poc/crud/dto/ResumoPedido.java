package com.poc.crud.dto;

import java.math.BigDecimal;

public record ResumoPedido(
        String nome, BigDecimal valor
) {
}
