package com.lucas.estoque.categoriaproduto;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

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

    public void deleteCategoriaProduto(int id) {
        if (!categoriaProdutoRepository.existsById(id)) {
            throw new EntityNotFoundException("Relação não encontrada");
        }
        categoriaProdutoRepository.deleteById(id);
    }

    public void deleteProdutoByCategoriaId(int CategoriaId) {
        var listaCategoriaProduto = categoriaProdutoRepository.findByCategoriaId(CategoriaId);
        listaCategoriaProduto
                .forEach(categoriaProduto -> deleteCategoriaProduto(categoriaProduto.getCategoriaProdutoId()));
    }

    public void deleteCategoriaByProdutoId(int produtoId) {
        var listaCategoriaProduto = categoriaProdutoRepository.findByProdutoId(produtoId);
        listaCategoriaProduto
                .forEach(categoriaProduto -> deleteCategoriaProduto(categoriaProduto.getCategoriaProdutoId()));
    }
}
