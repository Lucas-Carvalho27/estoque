package com.lucas.estoque.categoria;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CategoriaControler {

    private final CategoriaService categoriaService;

    public CategoriaControler(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/categoria")
    public CategoriaDTOResponse postMethodName(@Valid @RequestBody CategoriaDTO dto) {
        return categoriaService.saveCategoria(dto);
    }

    // update
    @PutMapping("/categoria/{id}")
    public CategoriaDTOResponse updateCategoria(@PathVariable int id, @Valid @RequestBody CategoriaDTO dto) {
        return categoriaService.updateCategoria(id, dto);
    }

    // delete
    @DeleteMapping("/categoria/{id}")
    public void deleteCategoria(@PathVariable int id) {
        categoriaService.deleteCategoria(id);
    }

    // read
    @GetMapping("/categoria/{id}")
    public CategoriaDTOResponse getCategoriaById(@PathVariable int id) {
        return categoriaService.findCategoriaById(id);
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
