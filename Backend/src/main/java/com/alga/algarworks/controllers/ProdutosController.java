package com.alga.algarworks.controllers;

import com.alga.algarworks.Exception.RecursoNaoEncontradoException;
import com.alga.algarworks.dtos.ProdutosDTO;
import com.alga.algarworks.services.ProdutosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProdutosController {

    private final ProdutosService produtosService;

    @GetMapping
    public ResponseEntity<Page<ProdutosDTO>> findAll(Pageable pageable
            , @RequestParam(defaultValue = "") String texto
            , @RequestParam(defaultValue = "") String campo) {
        return ResponseEntity.ok(produtosService.findAll(pageable, texto, campo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosDTO> findById(@PathVariable Long id) {
        ProdutosDTO produtosDTO = produtosService.findById(id);
        if (produtosDTO == null) {
            throw new RuntimeException("Produto não encontrado");
        }
        return ResponseEntity.ok(produtosService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProdutosDTO> save(@RequestBody ProdutosDTO dto) {
        dto = produtosService.save(dto);
        URI uri = URI.create("/products/" + dto.getId());
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutosDTO> update(@RequestBody ProdutosDTO dto, @PathVariable Long id) {
        dto = produtosService.update(dto, id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        produtosService.delete(id);
        return ResponseEntity.ok("Curso deletado com sucesso!");
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<String> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


}

