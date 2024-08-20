package com.lucas.estoque.produto;

import java.util.List;


public record ProdutoDTOResponse(
        int id,
        String ProdutoInfo,
        String ProdutoNome,
        List<String> categorias) {

}
