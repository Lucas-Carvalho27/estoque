package com.lucas.estoque.lojaproduto;

import org.springframework.stereotype.Service;

import com.lucas.estoque.loja.Loja;
import com.lucas.estoque.produto.Produto;

@Service
public class LojaProdutoMapperService {

    public LojaProduto toLojaProduto(LojaProdutoDTO lojaProdutoDTO) {
        var lojaproduto = new LojaProduto();

        lojaproduto.setPreco(lojaProdutoDTO.preco());
        lojaproduto.setQuantidade(lojaProdutoDTO.quantidade());

        var loja = new Loja();
        loja.setLoja_id(lojaProdutoDTO.lojaId());
        lojaproduto.setLoja(loja);

        var produto = new Produto();
        produto.setProdutoId(lojaProdutoDTO.produtoId());
        lojaproduto.setProduto(produto);

        return lojaproduto;
    }

    public LojaProdutoDTOResponse toLojaProdutoDTOResponse(LojaProduto lojaProduto) {

        return new LojaProdutoDTOResponse(lojaProduto.getProduto().getProdutoNome(), lojaProduto.getQuantidade(),
                lojaProduto.getPreco());
    }
}
