package com.lucas.estoque.lojaproduto;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LojaProdutoControler {

    private final LojaProdutoService lojaProdutoService;

    public LojaProdutoControler(LojaProdutoService lojaProdutoService) {
        this.lojaProdutoService = lojaProdutoService;
    }

    @PostMapping("lojaProduto")
    public LojaProdutoDTOResponse postMethodName(@Valid @RequestBody LojaProdutoDTO dto) {

        return lojaProdutoService.lojaProdutoSave(dto);
    }

    @GetMapping("lojas/{id}/produtos")
    public List<LojaProdutoDTOResponse> getProdutosByLojaId(@PathVariable("id") int lojaId) {
        return lojaProdutoService.getProdutosByLojaId(lojaId);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidExcption(MethodArgumentNotValidException exp) {

        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
}
