package com.lucas.estoque.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lucas.estoque.categoriaproduto.CategoriaProdutoService;

@Service
public class ProdutoMapperService {

    final private CategoriaProdutoService categoriaProdutoService; 

    public ProdutoMapperService(CategoriaProdutoService categoriaProdutoService) {
        this.categoriaProdutoService = categoriaProdutoService;
    }

    public Produto toProduto(ProdutoDTO produtoDTO) {
        var produto = new Produto();
        produto.setProdutoInfo(produtoDTO.ProdutoInfo());
        produto.setProdutoNome(produtoDTO.ProdutoNome());

        return produto;
    }

    public ProdutoDTOResponse toProdutoDTOResponse(Produto produto) {
        List<String> categorias = new ArrayList<>();
        categorias = categoriaProdutoService.findCategoriasProduto(produto.getId());
        System.out.println(categorias);
        return new ProdutoDTOResponse(produto.getId(),produto.getProdutoInfo(), produto.getProdutoNome(), categorias);
    }

}
