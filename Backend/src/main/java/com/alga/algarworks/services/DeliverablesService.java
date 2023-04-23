package com.alga.algarworks.services;

import com.alga.algarworks.Exception.InvalidDataException;
import com.alga.algarworks.dtos.DeliverablesDTO;
import com.alga.algarworks.entities.Deliverables;
import com.alga.algarworks.repositories.DeliverablesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    @Transactional(readOnly = true)
    public DeliverablesDTO findById(Long id) {
        log.info("Buscando produto com id: {}", id);
        Deliverables entity = deliverablesRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("",""));
        return entity.toDTO();
    }

    @Transactional
    public DeliverablesDTO update(DeliverablesDTO dto, Long id) {
        log.info("Atualizando produto: {}", dto);
        Deliverables entity = deliverablesRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(dto, entity, "id");
        entity = deliverablesRepository.save(entity);
        return entity.toDTO();
    }

    public void delete(Long id) {
        log.info("Deletando produto com id: {}", id);
        var deliverables = deliverablesRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("",""));
        deliverablesRepository.deleteById(deliverables.getId());
    }


}
