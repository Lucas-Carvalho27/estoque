package com.lucas.estoque.loja;

import java.util.Set;

import com.lucas.estoque.lojaproduto.LojaProduto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "loja")
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lojaNome;
    private String lojaCidade;
    private String lojaEstado;
    private int lojaNumero;
    private String lojaRua;

    @OneToMany(mappedBy = "loja", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<LojaProduto> lojaProdutos;

    public Loja() {
    }

    public int id() {
        return id;
    }

    public void setLoja_id(int id) {
        this.id = id;
    }

    public String getLojaNome() {
        return lojaNome;
    }

    public void setLojaNome(String lojaNome) {
        this.lojaNome = lojaNome;
    }

    public String getLojaCidade() {
        return lojaCidade;
    }

    public void setLojaCidade(String lojaCidade) {
        this.lojaCidade = lojaCidade;
    }

    public String getLojaEstado() {
        return lojaEstado;
    }

    public void setLojaEstado(String lojaEstado) {
        this.lojaEstado = lojaEstado;
    }

    public int getLojaNumero() {
        return lojaNumero;
    }

    public void setLojaNumero(int lojaNumero) {
        this.lojaNumero = lojaNumero;
    }

    public String getLojaRua() {
        return lojaRua;
    }

    public void setLojaRua(String lojaRua) {
        this.lojaRua = lojaRua;
    }

}
