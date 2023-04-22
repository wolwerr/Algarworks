package com.alga.algarworks.controllers;

import com.alga.algarworks.Exception.RecursoNaoEncontradoException;
import com.alga.algarworks.dtos.CursosDTO;
import com.alga.algarworks.dtos.OfertasDTO;
import com.alga.algarworks.services.OfertasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/v1/offers")
@RequiredArgsConstructor
@Slf4j
public class OfertasController {

    private final OfertasService ofertasService;

    @GetMapping
    public ResponseEntity<Page<OfertasDTO>> findAll(Pageable pageable
            , @RequestParam(defaultValue = "") String texto
            , @RequestParam(defaultValue = "") String campo) {
        return ResponseEntity.ok(ofertasService.findAll(pageable, texto, campo));
    }

    @GetMapping("/{code}")
    public ResponseEntity<OfertasDTO> findById(@PathVariable String code) {
        OfertasDTO ofertasDTO = ofertasService.findById(code);
        if (ofertasDTO == null) {
            throw new RecursoNaoEncontradoException("Produto não encontrado");
        }
        return ResponseEntity.ok(ofertasService.findById(code));
    }

    @PostMapping
    public ResponseEntity<OfertasDTO> insert(@RequestBody OfertasDTO dto) {
        dto = ofertasService.save(dto);
        URI uri = URI.create("/offers/" + dto.getCode());
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{code}")
    public ResponseEntity<OfertasDTO> update(@RequestBody OfertasDTO dto, @PathVariable String code) {
        dto = ofertasService.update(dto, code);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> delete(@PathVariable String code) {
        ofertasService.delete(code);
        return ResponseEntity.ok("Curso deletado com sucesso!");
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<String> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}

