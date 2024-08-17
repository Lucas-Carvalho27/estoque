package com.lucas.estoque.loja;

import jakarta.validation.constraints.NotNull;

public record LojaDTO(
        @NotNull String lojaNome,
        @NotNull String lojaCidade,
        @NotNull String lojaEstado,
        @NotNull int lojaNumero,
        @NotNull String lojaRua) {
}
