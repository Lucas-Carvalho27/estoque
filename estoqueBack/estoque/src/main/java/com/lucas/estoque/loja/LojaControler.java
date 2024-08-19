package com.lucas.estoque.loja;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin("http://localhost:3000")
public class LojaControler {
    private final LojaService lojaService;

    public LojaControler(LojaService lojaService) {
        this.lojaService = lojaService;
    }

    @PostMapping("loja")
    public LojaDTORespose saveLoja(@Valid @RequestBody LojaDTO dto) {
        return lojaService.saveLoja(dto);
    }

    // update
    @PutMapping("/loja/{id}")
    public LojaDTORespose updateLoja(@PathVariable int id, @Valid @RequestBody LojaDTO dto) {
        return lojaService.updateLoja(id, dto);
    }

    // delete
    @DeleteMapping("/loja/{id}")
    public void deleteLoja(@PathVariable int id) {
        lojaService.deleteLoja(id);
    }

    // read
    @GetMapping("/loja/{id}")
    public LojaDTORespose findLojaById(@PathVariable int id) {
        return lojaService.findLojaById(id);
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
