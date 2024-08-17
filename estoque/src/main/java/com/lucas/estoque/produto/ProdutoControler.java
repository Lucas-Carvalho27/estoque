package com.lucas.estoque.produto;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ProdutoControler {

    private final ProdutoService produtoService;

    public ProdutoControler(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/produto")
    public ProdutoDTOResponse savePorduto(@Valid @RequestBody ProdutoDTO dto) {
        return produtoService.saveProduto(dto);
    }

}
