package com.lucas.estoque.categoria;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.var;

@Service
public class CategoriaService {

    final private CategoriaMapperService categoriaMapperService;
    final private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaMapperService categoriaMapperService, CategoriaRepository categoriaRepository) {
        this.categoriaMapperService = categoriaMapperService;
        this.categoriaRepository = categoriaRepository;
    }

    // Create
    public CategoriaDTOResponse saveCategoria(CategoriaDTO dto) {
        var categoria = categoriaMapperService.toCategoria(dto);
        var savedCategoria = categoriaRepository.save(categoria);
        return categoriaMapperService.toCategoriaDTOResponse(savedCategoria);
    }

    // Update by ID
    public CategoriaDTOResponse updateCategoria(int id, CategoriaDTO dto) {
        var categoria = categoriaMapperService.toCategoria(dto);
        categoria.setId(id);
        var categoriaExistente = categoriaRepository.findById(categoria.getId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        // Atualize os campos da categoria existente com os valores do DTO
        categoriaExistente.setCategoriaNome(categoria.getCategoriaNome());
        // Atualize outros campos conforme necessário

        var categoriaAtualizada = categoriaRepository.save(categoriaExistente);
        return categoriaMapperService.toCategoriaDTOResponse(categoriaAtualizada);
    }

    // Delete ID
    public void deleteCategoria(int id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada");
        }
        categoriaRepository.deleteById(id);
    }

    // Read using ID
    public CategoriaDTOResponse findCategoriaById(int id) {
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        return categoriaMapperService.toCategoriaDTOResponse(categoria);
    }

}
