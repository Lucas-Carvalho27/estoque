package com.lucas.estoque.categoria;

import org.springframework.stereotype.Service;

@Service
public class CategoriaMapperService {

    public Categoria toCategoria(CategoriaDTO dto) {
        var categoria = new Categoria();
        categoria.setCategoriaNome(dto.categoriaNome());
        return categoria;
    }

    public CategoriaDTOResponse toCategoriaDTOResponse(Categoria categoria) {
        return new CategoriaDTOResponse(categoria.getCategoriaNome());
    }
}
