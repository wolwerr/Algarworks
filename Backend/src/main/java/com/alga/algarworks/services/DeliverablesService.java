package com.alga.algarworks.services;

import com.alga.algarworks.dtos.DeliverablesDTO;
import com.alga.algarworks.entities.Deliverables;
import com.alga.algarworks.repositories.DeliverablesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliverablesService {

    private final DeliverablesRepository deliverablesRepository;

    @Transactional(readOnly = true)
    public List<Deliverables> findAll() {
        return deliverablesRepository.findAll();
    }
    @Transactional
    public DeliverablesDTO save(DeliverablesDTO dto) {
        log.info("Salvando Deliverables: {}", dto);
        Deliverables deliverables = dto.toEntity();
        deliverables.setCourse(dto.getCourse());
        deliverables = deliverablesRepository.save(deliverables);
        return deliverables.toDTO();
    }


}
