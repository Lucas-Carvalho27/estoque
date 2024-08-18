package com.lucas.estoque.lojaproduto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lucas.estoque.loja.Loja;
import com.lucas.estoque.loja.LojaRepository;
import com.lucas.estoque.produto.Produto;
import com.lucas.estoque.produto.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LojaProdutoService {

    private final LojaProdutoMapperService lojaProdutoMapperService;
    private final LojaProdutoRepository lojaProdutoRepository;
    private final LojaRepository lojaRepository;
    private final ProdutoRepository produtoRepository;

    public LojaProdutoService(LojaProdutoMapperService lojaProdutoMapperService,
            LojaProdutoRepository lojaProdutoRepository, LojaRepository lojaRepository,
            ProdutoRepository produtoRepository) {
        this.lojaProdutoMapperService = lojaProdutoMapperService;
        this.lojaProdutoRepository = lojaProdutoRepository;
        this.lojaRepository = lojaRepository;
        this.produtoRepository = produtoRepository;
    }

    public void addVariasLojas(int produtoId) {
        List<Loja> lojas = lojaRepository.findAll();
        List<LojaProduto> lojaProdutos = new ArrayList<>();
        int tamanho = lojas.size();
        for (int i = 0; i < tamanho; i++) {
            Loja loja = lojas.get(i);
            LojaProdutoDTO dto = new LojaProdutoDTO(loja.getId(), produtoId, 0, 9999);
            lojaProdutos.add(lojaProdutoMapperService.toLojaProduto(dto));
        }
        lojaProdutoRepository.saveAll(lojaProdutos);
    }

    /// referencia
    public void addVariosProdutos(int lojaId) {
        List<Produto> produtos = produtoRepository.findAll();
        List<LojaProduto> lojaProdutos = new ArrayList<>();
        int tamanho = produtos.size();
        for (int i = 0; i < tamanho; i++) {
            Produto produto = produtos.get(i);
            LojaProdutoDTO dto = new LojaProdutoDTO(lojaId, produto.getProdutoId(), 0, 9999);
            lojaProdutos.add(lojaProdutoMapperService.toLojaProduto(dto));
        }
        lojaProdutoRepository.saveAll(lojaProdutos);
    }

    public LojaProdutoDTOResponse lojaProdutoSave(LojaProdutoDTO dto) {
        var lojaProduto = lojaProdutoMapperService.toLojaProduto(dto);
        var savedLojaProduto = lojaProdutoRepository.save(lojaProduto);
        return lojaProdutoMapperService.toLojaProdutoDTOResponse(savedLojaProduto);

    }

    public void deleteProdutosByLojaId(int lojaId) {
        var listaLojaProduto = lojaProdutoRepository.findByLojaId(lojaId);
        listaLojaProduto.forEach(lojaProduto -> deleteProdutoLoja(lojaProduto.getLoja_produto_id()));
    }

    public void deleteLojaByProdutoId(int produtoId) {
        var listaLojaProduto = lojaProdutoRepository.findByProdutoId(produtoId);
        listaLojaProduto.forEach(lojaProduto -> deleteProdutoLoja(lojaProduto.getLoja_produto_id()));
    }

    public List<LojaProdutoDTOResponse> getProdutosByLojaId(int lojaId) {
        var listaLojaProduto = lojaProdutoRepository.findByLojaId(lojaId);
        return listaLojaProduto.stream().map(lojaProdutoMapperService::toLojaProdutoDTOResponse)
                .collect(Collectors.toList());
    }

    public List<LojaProdutoDTOResponse> getLojasByProdutoId(int produtoId) {
        var listaLojaProduto = lojaProdutoRepository.findByProdutoId(produtoId);
        return listaLojaProduto.stream().map(lojaProdutoMapperService::toLojaProdutoDTOResponse)
                .collect(Collectors.toList());
    }

    // Delete ID
    public void deleteProdutoLoja(int id) {
        if (!lojaProdutoRepository.existsById(id)) {
            throw new EntityNotFoundException("ProdutoLoja não encontrada");
        }
        lojaProdutoRepository.deleteById(id);
    }

}
