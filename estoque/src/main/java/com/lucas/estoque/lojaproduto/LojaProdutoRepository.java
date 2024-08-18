package com.lucas.estoque.lojaproduto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface LojaProdutoRepository extends JpaRepository<LojaProduto, Integer> {
    List<LojaProduto> findByLojaId(int id);

    List<LojaProduto> findByProdutoId(int id);
}
