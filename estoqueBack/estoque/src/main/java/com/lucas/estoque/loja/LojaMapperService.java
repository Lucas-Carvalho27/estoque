package com.lucas.estoque.loja;

import org.springframework.stereotype.Service;

@Service
public class LojaMapperService {
    public Loja toLoja(LojaDTO lojaDTO) {
        var loja = new Loja();

        loja.setLojaNome(lojaDTO.lojaNome());
        loja.setLojaEstado(lojaDTO.lojaEstado());
        loja.setLojaCidade(lojaDTO.lojaCidade());
        loja.setLojaRua(lojaDTO.lojaRua());
        loja.setLojaNumero(lojaDTO.lojaNumero());

        return loja;
    }

    public LojaDTORespose toLojaDTORespose(Loja loja) {

        return new LojaDTORespose(loja.getLojaNome(), loja.getLojaCidade(), loja.getLojaEstado(),
                loja.getLojaNumero(), loja.getLojaRua());
    }

}
