package com.lucas.estoque.lojaproduto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class LojaProdutoService {

    private final LojaProdutoMapperService lojaProdutoMapperService;
    private final LojaProdutoRepository lojaProdutoRepository;

    public LojaProdutoService(LojaProdutoMapperService lojaProdutoMapperService,
            LojaProdutoRepository lojaProdutoRepository) {
        this.lojaProdutoMapperService = lojaProdutoMapperService;
        this.lojaProdutoRepository = lojaProdutoRepository;
    }

    public LojaProdutoDTOResponse lojaProdutoSave(LojaProdutoDTO dto) {
        var lojaProduto = lojaProdutoMapperService.toLojaProduto(dto);
        var savedLojaProduto = lojaProdutoRepository.save(lojaProduto);
        return lojaProdutoMapperService.toLojaProdutoDTOResponse(savedLojaProduto);

    }

    public List<LojaProdutoDTOResponse> getProdutosByLojaId(int lojaId) {
        var listaLojaProduto = lojaProdutoRepository.findByLojaId(lojaId);
        return listaLojaProduto.stream().map(lojaProdutoMapperService::toLojaProdutoDTOResponse)
                .collect(Collectors.toList());
    }

}
