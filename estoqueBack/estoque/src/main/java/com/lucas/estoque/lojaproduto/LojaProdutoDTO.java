package com.lucas.estoque.lojaproduto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LojaProdutoDTO(
                @NotNull int lojaId,
                @NotNull int produtoId,
                @NotNull @Min(value = 0, message = "A quantidade de intens no estoque não pode ser negativa.") int quantidade,
                @NotNull @DecimalMin(value = "0.0", inclusive = false, message = "O preço do produto deve ser maior do que 0.") float preco) {

}
