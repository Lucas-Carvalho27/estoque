package com.lucas.estoque.produto;

import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapperService produtoMapperService;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapperService produtoMapperService) {
        this.produtoRepository = produtoRepository;
        this.produtoMapperService = produtoMapperService;
    }

    public ProdutoDTOResponse saveProduto(ProdutoDTO dto) {
        var produto = produtoMapperService.toProduto(dto);
        var savedProduct = produtoRepository.save(produto);
        return produtoMapperService.toProdutoDTOResponse(savedProduct);
    }

}
