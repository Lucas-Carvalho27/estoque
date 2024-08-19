package com.lucas.estoque.categoria;

import java.util.Set;

import com.lucas.estoque.categoriaproduto.CategoriaProduto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String categoriaNome;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
    private Set<CategoriaProduto> categoriaProduto;

    public Categoria() {
    }

    public Set<CategoriaProduto> getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(Set<CategoriaProduto> categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

}