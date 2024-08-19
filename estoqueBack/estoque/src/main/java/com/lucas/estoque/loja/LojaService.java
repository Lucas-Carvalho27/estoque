package com.lucas.estoque.loja;

import org.springframework.stereotype.Service;

import com.lucas.estoque.lojaproduto.LojaProdutoMapperService;
import com.lucas.estoque.lojaproduto.LojaProdutoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LojaService {

    private final LojaRepository lojaRepository;
    private final LojaMapperService lojaMappperService;
    private final LojaProdutoService lojaProdutoService;

    public LojaService(LojaRepository lojaRepository, LojaMapperService lojaMappperService,
            LojaProdutoService lojaProdutoService) {
        this.lojaRepository = lojaRepository;
        this.lojaMappperService = lojaMappperService;
        this.lojaProdutoService = lojaProdutoService;
    }

    public LojaDTORespose saveLoja(LojaDTO dto) {
        var loja = lojaMappperService.toLoja(dto);
        var savedLoja = lojaRepository.save(loja);
        lojaProdutoService.addVariosProdutos(loja.getId());
        return lojaMappperService.toLojaDTORespose(savedLoja);
    }

    // Update by ID
    public LojaDTORespose updateLoja(int id, LojaDTO dto) {
        var loja = lojaMappperService.toLoja(dto);
        loja.setId(id);
        var lojaExistente = lojaRepository.findById(loja.getId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        // Atualize os campos da categoria existente com os valores do DTO
        lojaExistente.setLojaNome(loja.getLojaNome());
        lojaExistente.setLojaCidade(loja.getLojaCidade());
        lojaExistente.setLojaEstado(loja.getLojaEstado());
        lojaExistente.setLojaNumero(loja.getLojaNumero());

        var lojaAtualizada = lojaRepository.save(lojaExistente);
        return lojaMappperService.toLojaDTORespose(lojaAtualizada);
    }

    // Delete ID
    public void deleteLoja(int id) {
        if (!lojaRepository.existsById(id)) {
            throw new EntityNotFoundException("Loja não encontrada");
        }
        lojaProdutoService.deleteProdutosByLojaId(id);
        lojaRepository.deleteById(id);
    }

    // Read using ID
    public LojaDTORespose findLojaById(int id) {
        var loja = lojaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loja não encontrada"));
        return lojaMappperService.toLojaDTORespose(loja);
    }

}
