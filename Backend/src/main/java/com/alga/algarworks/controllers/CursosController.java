package com.alga.algarworks.controllers;

import com.alga.algarworks.Exception.RecursoNaoEncontradoException;
import com.alga.algarworks.dtos.CursosDTO;
import com.alga.algarworks.services.CursosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/courses")
@RequiredArgsConstructor
@Slf4j
public class CursosController {

    private final CursosService cursosService;

    @GetMapping
    public ResponseEntity<Page<CursosDTO>> findAll(Pageable pageable
            , @RequestParam(defaultValue = "") String texto
            , @RequestParam(defaultValue = "") String campo) {
        return ResponseEntity.ok(cursosService.findAll(pageable, texto, campo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursosDTO> findById(@PathVariable Long id) {
        CursosDTO cursosDTO = cursosService.findById(id);
        if (cursosDTO == null) {
            throw new RecursoNaoEncontradoException("Produto não encontrado");
        }
        return ResponseEntity.ok(cursosService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CursosDTO> save(@RequestBody CursosDTO dto) {
        dto = cursosService.save(dto);
        URI uri = URI.create("/courses/" + dto.getId());
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursosDTO> update(@RequestBody CursosDTO dto, @PathVariable Long id) {
        dto = cursosService.update(dto, id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        cursosService.delete(id);
        return ResponseEntity.ok("Curso deletado com sucesso!");
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<String> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
