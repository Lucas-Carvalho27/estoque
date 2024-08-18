package com.lucas.estoque.produto;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

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

    // Update by ID
    public ProdutoDTOResponse updateProduto(int id, ProdutoDTO dto) {
        var produto = produtoMapperService.toProduto(dto);
        produto.setId(id);
        var produtoExistente = produtoRepository.findById(produto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrada"));

        // Atualize os campos da categoria existente com os valores do DTO

        produtoExistente.setProdutoInfo(produto.getProdutoInfo());
        produtoExistente.setProdutoNome(produto.getProdutoNome());

        var produtoAtualizado = produtoRepository.save(produtoExistente);
        return produtoMapperService.toProdutoDTOResponse(produtoAtualizado);
    }

    // Delete ID
    public void deleteProduto(int id) {
        if (!produtoRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto não encontrada");
        }
        produtoRepository.deleteById(id);
    }

    // Read using ID
    public ProdutoDTOResponse findProdutoById(int id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrada"));
        return produtoMapperService.toProdutoDTOResponse(produto);
    }

}
