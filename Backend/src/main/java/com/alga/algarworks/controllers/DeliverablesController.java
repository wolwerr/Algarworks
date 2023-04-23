package com.alga.algarworks.controllers;

import com.alga.algarworks.dtos.DeliverablesDTO;
import com.alga.algarworks.entities.Deliverables;
import com.alga.algarworks.services.DeliverablesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

}
