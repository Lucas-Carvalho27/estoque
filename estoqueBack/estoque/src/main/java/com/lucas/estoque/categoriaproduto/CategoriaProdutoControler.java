package com.lucas.estoque.categoriaproduto;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin("http://localhost:3000")
public class CategoriaProdutoControler {
    final private CategoriaProdutoService categoriaProdutoService;

    public CategoriaProdutoControler(CategoriaProdutoService categoriaProdutoService) {
        this.categoriaProdutoService = categoriaProdutoService;
    }

    @PostMapping("/CategoriaProduto")
    public CategoriaProdutoDTOResponse postMethodName(@Valid @RequestBody CategoriaProdutoDTO dto) {
        return categoriaProdutoService.saveCategoriaProduto(dto);
    }

    @DeleteMapping("/categodiaProduto/{id}")
    public void deleteCategoriaProduto(@PathVariable int id) {
        categoriaProdutoService.deleteCategoriaProduto(id);
    }

}
