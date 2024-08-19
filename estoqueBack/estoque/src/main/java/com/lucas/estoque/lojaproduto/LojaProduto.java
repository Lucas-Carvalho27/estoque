package com.lucas.estoque.lojaproduto;

import com.lucas.estoque.loja.Loja;
import com.lucas.estoque.produto.Produto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "loja_produto")
public class LojaProduto implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loja_produto_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loja_id")
    private Loja loja;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "produto_quantidade")
    private Integer quantidade;

    @Column(name = "produto_preco")
    private float preco;

    public LojaProduto() {
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int loja_produto_id() {
        return loja_produto_id;
    }

    public void loja_produto_id(int loja_produto_id) {
        this.loja_produto_id = loja_produto_id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

}
