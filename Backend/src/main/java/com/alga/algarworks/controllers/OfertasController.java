package com.alga.algarworks.controllers;

import com.alga.algarworks.Exception.ErrorObject;
import com.alga.algarworks.Exception.InvalidDataException;
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
            throw new InvalidDataException("","");
        }
        return ResponseEntity.ok(ofertasService.findById(code));
    }

    @PostMapping
    public ResponseEntity<OfertasDTO> insert(@RequestBody OfertasDTO dto) {
        if (dto.getCode() == null ||
                dto.getInternalName() == null ||
                dto.getSalesStartingAt() == null ||
                dto.getSalesEndingAt() == null ||
                dto.getProduct() == null ||
                dto.getDeliverables() == null ||
                dto.getSupportDurationInDays() == null
        ) {
            throw new InvalidDataException(
                    "amount",
                    "O valor é obrigatório"
            );
        }
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
        this.ofertasService.delete(code);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex) {
        ErrorObject errorResponse = new ErrorObject(
                ex.getName(),
                ex.getUserMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}

