package com.lucas.estoque.categoriaproduto;

import com.lucas.estoque.categoria.Categoria;
import com.lucas.estoque.produto.Produto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CategoriaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CategoriaProdutoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public CategoriaProduto() {
    }

    public int getCategoriaProdutoId() {
        return CategoriaProdutoId;
    }

    public void setCategoriaProdutoId(int categoriaProdutoId) {
        CategoriaProdutoId = categoriaProdutoId;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
