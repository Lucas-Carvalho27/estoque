package com.lucas.estoque.produto;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
public class ProdutoControler {

    private final ProdutoService produtoService;

    public ProdutoControler(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/produto")
    public ProdutoDTOResponse savePorduto(@Valid @RequestBody ProdutoDTO dto) {
        return produtoService.saveProduto(dto);
    }

    // update
    @PutMapping("/produto/{id}")
    public ProdutoDTOResponse updateProduto(@PathVariable int id, @Valid @RequestBody ProdutoDTO dto) {
        return produtoService.updateProduto(id, dto);
    }

    // delete
    @DeleteMapping("/produto/{id}")
    public void deleteProduto(@PathVariable int id) {
        produtoService.deleteProduto(id);
    }

    // read
    @GetMapping("/produto/{id}")
    public ProdutoDTOResponse findProdutoById(@PathVariable int id) {
        return produtoService.findProdutoById(id);
    }

    @GetMapping("/produto/all")
    public List<ProdutoDTOResponse> getAllProdutos() {
        return produtoService.getAllProdutos();
    }

}
