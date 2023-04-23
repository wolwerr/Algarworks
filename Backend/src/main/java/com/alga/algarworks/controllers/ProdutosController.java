package com.alga.algarworks.controllers;

import com.alga.algarworks.Exception.ErrorObject;
import com.alga.algarworks.Exception.InvalidDataException;
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
import java.time.Instant;
import java.util.List;

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
        if (dto.getName() == null) {
            throw new InvalidDataException(

                    "https://algaworks.com/dados-invalidos",
                    "Dados inválidos"

            );
        }

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

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex) {
        ErrorObject errorResponse = new ErrorObject(
                ex.getName(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

