package com.lucas.estoque.categoriaproduto;

import jakarta.validation.constraints.NotNull;

public record CategoriaProdutoDTO(
                @NotNull int categoriaId,
                @NotNull int produtoId

) {

}
