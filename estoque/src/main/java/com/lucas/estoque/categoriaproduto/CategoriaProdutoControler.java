package com.lucas.estoque.categoriaproduto;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CategoriaProdutoControler {
    final private CategoriaProdutoService categoriaProdutoService;

    public CategoriaProdutoControler(CategoriaProdutoService categoriaProdutoService) {
        this.categoriaProdutoService = categoriaProdutoService;
    }

    @PostMapping("/CategoriaProduto")
    public CategoriaProdutoDTOResponse postMethodName(@Valid @RequestBody CategoriaProdutoDTO dto) {
        return categoriaProdutoService.saveCategoriaProduto(dto);
    }

}
