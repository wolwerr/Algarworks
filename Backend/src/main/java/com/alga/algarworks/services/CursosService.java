package com.alga.algarworks.services;

import com.alga.algarworks.Exception.RecursoNaoEncontradoException;
import com.alga.algarworks.dtos.CursosDTO;
import com.alga.algarworks.entities.Cursos;
import com.alga.algarworks.repositories.CursosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CursosService {

    private final CursosRepository cursosRepository;

    @Transactional(readOnly = true)
    public Page<CursosDTO> findAll(Pageable pageable, String texto, String campo) {
        return switch (campo) {
            case "id" -> cursosRepository.findByIdContainingIgnoreCase(texto, pageable).map(Cursos::toDTO);
            case "name" -> cursosRepository.findByNameContainingIgnoreCase(texto, pageable).map(Cursos::toDTO);
            default -> cursosRepository.findAll(pageable).map(Cursos::toDTO);
        };
    }

    @Transactional(readOnly = true)
    public CursosDTO findById(Long id) {
        log.info("Buscando curso com id: {}", id);
        Cursos entity = cursosRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Curso não encontrado: " + id));
        return entity.toDTO();
    }

    @Transactional
    public CursosDTO save(CursosDTO dto) {
        log.info("Salvando curso: {}", dto);
        Cursos cursos = dto.toEntity();
        cursos = cursosRepository.save(cursos);
        return cursos.toDTO();
    }

    @Transactional
    public CursosDTO update(CursosDTO dto, Long id) {
        log.info("Atualizando curso: {}", dto);
        Cursos entity = cursosRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(dto, entity, "id");
        entity = cursosRepository.save(entity);
        return entity.toDTO();
    }

    public void delete(Long id) {
        log.info("Deletando curso com id: {}", id);
        var cursos = cursosRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Curso não encontrado: " + id));
        cursosRepository.deleteById(cursos.getId());
    }
}
