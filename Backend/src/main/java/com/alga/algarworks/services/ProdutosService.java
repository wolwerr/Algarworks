package com.alga.algarworks.services;

import com.alga.algarworks.Exception.InvalidDataException;
import com.alga.algarworks.dtos.ProdutosDTO;
import com.alga.algarworks.entities.Produtos;
import com.alga.algarworks.repositories.ProdutosRepository;
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
public class ProdutosService {

    private final ProdutosRepository produtosRepository;

    @Transactional(readOnly = true)
    public Page<ProdutosDTO> findAll(Pageable pageable, String texto, String campo) {
        return switch (campo) {
            case "id" -> produtosRepository.findByIdContainingIgnoreCase(texto, pageable).map(Produtos::toDTO);
            case "name" -> produtosRepository.findByNameContainingIgnoreCase(texto, pageable).map(Produtos::toDTO);
            default -> produtosRepository.findAll(pageable).map(Produtos::toDTO);
        };
    }

    @Transactional(readOnly = true)
    public ProdutosDTO findById(Long id) {
        log.info("Buscando produto com id: {}", id);
        Produtos entity = produtosRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("",""));
        return entity.toDTO();
    }

    @Transactional
    public ProdutosDTO save(ProdutosDTO dto) {
        log.info("Salvando produto: {}", dto);
        Produtos produtos = dto.toEntity();
        produtos = produtosRepository.save(produtos);
        return produtos.toDTO();
    }

    @Transactional
    public ProdutosDTO update(ProdutosDTO dto, Long id) {
        log.info("Atualizando produto: {}", dto);
        Produtos entity = produtosRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(dto, entity, "id");
        entity = produtosRepository.save(entity);
        return entity.toDTO();
    }

    public void delete(Long id) {
        log.info("Deletando produto com id: {}", id);
        var produtos = produtosRepository.findById(id)
                .orElseThrow(() -> new InvalidDataException("",""));
        produtosRepository.deleteById(produtos.getId());
    }
}
