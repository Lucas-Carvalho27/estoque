package com.lucas.estoque.produto;

import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(
                @NotNull String ProdutoInfo,
                @NotNull String ProdutoNome) {

}
