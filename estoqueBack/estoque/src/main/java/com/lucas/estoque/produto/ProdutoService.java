package com.lucas.estoque.produto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lucas.estoque.lojaproduto.LojaProdutoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapperService produtoMapperService;
    private final LojaProdutoService lojaProdutoService;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapperService produtoMapperService,
            LojaProdutoService lojaProdutoService) {
        this.produtoRepository = produtoRepository;
        this.produtoMapperService = produtoMapperService;
        this.lojaProdutoService = lojaProdutoService;
    }

    public ProdutoDTOResponse saveProduto(ProdutoDTO dto) {
        var produto = produtoMapperService.toProduto(dto);
        var savedProduct = produtoRepository.save(produto);
        lojaProdutoService.addVariasLojas(produto.getId());
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
        lojaProdutoService.deleteLojaByProdutoId(id);
        produtoRepository.deleteById(id);
    }

    // Read using ID
    public ProdutoDTOResponse findProdutoById(int id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrada"));
        return produtoMapperService.toProdutoDTOResponse(produto);
    }

    public List<ProdutoDTOResponse>  getAllProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                       .map(produtoMapperService::toProdutoDTOResponse)
                       .collect(Collectors.toList());
    }
}
