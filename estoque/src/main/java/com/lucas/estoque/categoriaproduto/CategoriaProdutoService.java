package com.lucas.estoque.categoriaproduto;

import org.springframework.stereotype.Service;

@Service
public class CategoriaProdutoService {

    final private CategoriaProdutoRepository categoriaProdutoRepository;
    final private CategoriaProdutoMapperService categoriaProdutoMapperService;

    public CategoriaProdutoService(CategoriaProdutoRepository categoriaProdutoRepository,
            CategoriaProdutoMapperService categoriaProdutoMapperService) {
        this.categoriaProdutoRepository = categoriaProdutoRepository;
        this.categoriaProdutoMapperService = categoriaProdutoMapperService;
    }

    public CategoriaProdutoDTOResponse saveCategoriaProduto(CategoriaProdutoDTO dto) {
        var categodiaProduto = categoriaProdutoMapperService.toCategoriaProduto(dto);
        var savedCategodiaProduto = categoriaProdutoRepository.save(categodiaProduto);
        return categoriaProdutoMapperService.toCategoriaProdutoDTOResponse(savedCategodiaProduto);
    }
}
