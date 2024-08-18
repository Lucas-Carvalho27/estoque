package com.lucas.estoque.categoriaproduto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Integer> {
    List<CategoriaProduto> findByProdutoId(int id);

    List<CategoriaProduto> findByCategoriaId(int id);
}
