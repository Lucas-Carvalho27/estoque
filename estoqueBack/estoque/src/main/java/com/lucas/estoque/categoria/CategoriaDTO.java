package com.lucas.estoque.categoria;

import jakarta.validation.constraints.NotNull;

public record CategoriaDTO(
                @NotNull String categoriaNome) {

}
