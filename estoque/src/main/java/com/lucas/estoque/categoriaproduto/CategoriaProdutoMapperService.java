package com.lucas.estoque.categoriaproduto;

import org.springframework.stereotype.Service;

import com.lucas.estoque.categoria.Categoria;
import com.lucas.estoque.produto.Produto;

@Service
public class CategoriaProdutoMapperService {
    public CategoriaProduto toCategoriaProduto(CategoriaProdutoDTO dto) {

        var categoriaProduto = new CategoriaProduto();

        var produto = new Produto();
        produto.setId(dto.produtoId());
        categoriaProduto.setProduto(produto);

        var categoria = new Categoria();
        categoria.setId(dto.categoriaId());
        categoriaProduto.setCategoria(categoria);

        return categoriaProduto;
    }

    public CategoriaProdutoDTOResponse toCategoriaProdutoDTOResponse(CategoriaProduto categoriaProduto) {
        return new CategoriaProdutoDTOResponse();
    }
}
