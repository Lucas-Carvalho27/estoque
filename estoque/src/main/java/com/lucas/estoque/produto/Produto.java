package com.lucas.estoque.produto;

import java.util.Set;

import com.lucas.estoque.categoriaproduto.CategoriaProduto;
import com.lucas.estoque.lojaproduto.LojaProduto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "produto_info")
    private String produtoInfo;
    @Column(name = "produto_nome")
    private String produtoNome;

    @OneToMany(mappedBy = "produto")
    private Set<LojaProduto> lojaProdutos;

    @OneToMany(mappedBy = "produto")
    private Set<CategoriaProduto> categoriaProdutos;

    public Produto() {
    }

    public int getProdutoId() {
        return id;
    }

    public void setProdutoId(int id) {
        this.id = id;
    }

    public String getProdutoInfo() {
        return produtoInfo;
    }

    public void setInfo(String produtoInfo) {
        this.produtoInfo = produtoInfo;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

}
