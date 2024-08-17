package com.lucas.estoque.loja;

import org.springframework.stereotype.Service;

@Service
public class LojaService {

    private final LojaRepository lojaRepository;
    private final LojaMapperService lojaMappperService;

    public LojaService(LojaRepository lojaRepository, LojaMapperService lojaMappperService) {
        this.lojaRepository = lojaRepository;
        this.lojaMappperService = lojaMappperService;
    }

    public LojaDTORespose saveLoja(LojaDTO dto) {
        var loja = lojaMappperService.toLoja(dto);
        var savedLoja = lojaRepository.save(loja);
        return lojaMappperService.toLojaDTORespose(savedLoja);
    }

}
