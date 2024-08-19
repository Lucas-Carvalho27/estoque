package com.lucas.estoque.produto;

import org.springframework.stereotype.Service;

@Service
public class ProdutoMapperService {

    public Produto toProduto(ProdutoDTO produtoDTO) {
        var produto = new Produto();
        produto.setProdutoInfo(produtoDTO.ProdutoInfo());
        produto.setProdutoNome(produtoDTO.ProdutoNome());

        return produto;
    }

    public ProdutoDTOResponse toProdutoDTOResponse(Produto produto) {

        return new ProdutoDTOResponse(produto.getProdutoInfo(), produto.getProdutoNome());
    }

}
