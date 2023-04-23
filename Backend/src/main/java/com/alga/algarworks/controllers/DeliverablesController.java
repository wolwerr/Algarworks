package com.alga.algarworks.controllers;

import com.alga.algarworks.Exception.ErrorObject;
import com.alga.algarworks.Exception.InvalidDataException;
import com.alga.algarworks.dtos.DeliverablesDTO;
import com.alga.algarworks.entities.Deliverables;
import com.alga.algarworks.services.DeliverablesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/deliverables")
@RequiredArgsConstructor
@Slf4j
public class DeliverablesController {

    private final DeliverablesService deliverablesService;

    @GetMapping
    public ResponseEntity<List<Deliverables>> findAll() {
        return ResponseEntity.ok(deliverablesService.findAll());
    }

    @PostMapping
    public ResponseEntity<DeliverablesDTO> insert(@RequestBody DeliverablesDTO dto) {
        dto = deliverablesService.save(dto);
        URI uri = URI.create("/deliverables/" + dto.getId());
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliverablesDTO> findById(@PathVariable Long id) {
        DeliverablesDTO deliverablesDTO = deliverablesService.findById(id);
        if (deliverablesDTO == null) {
            throw new RuntimeException("Deliverables não encontrado");
        }
        return ResponseEntity.ok(deliverablesService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliverablesDTO> update(@RequestBody DeliverablesDTO dto, @PathVariable Long id) {
        dto = deliverablesService.update(dto, id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        deliverablesService.delete(id);
        return ResponseEntity.ok("Deliverables deletado com sucesso!");
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
