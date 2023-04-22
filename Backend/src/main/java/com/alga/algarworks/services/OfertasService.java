package com.alga.algarworks.services;

import com.alga.algarworks.Exception.RecursoNaoEncontradoException;
import com.alga.algarworks.dtos.OfertasDTO;
import com.alga.algarworks.entities.Ofertas;
import com.alga.algarworks.repositories.OfertasRepository;
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
public class OfertasService {

    private final OfertasRepository ofertasRepository;

    @Transactional(readOnly = true)
    public Page<OfertasDTO> findAll(Pageable pageable, String texto, String campo) {
        return switch (campo) {
            case "code" -> ofertasRepository.findByCodeContainingIgnoreCase(texto, pageable).map(Ofertas::toDTO);
            case "internalName" -> ofertasRepository.findByInternalNameContainingIgnoreCase(texto, pageable).map(Ofertas::toDTO);
            case "active" -> ofertasRepository.findByActiveContainingIgnoreCase(Boolean.parseBoolean(texto), pageable).map(Ofertas::toDTO);
            case "supportDurationInDays" -> ofertasRepository.findBySupportDurationInDaysContainingIgnoreCase(texto, pageable).map(Ofertas::toDTO);
            default -> ofertasRepository.findAll(pageable).map(Ofertas::toDTO);
        };
    }

    @Transactional(readOnly = true)
    public OfertasDTO findById(String code) {
        log.info("Buscando oferta por ID: {}", code);
        Ofertas ofertas = ofertasRepository.findById(code)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Oferta não encontrada id: " + code));
        return ofertas.toDTO();
    }

    @Transactional
    public OfertasDTO save(OfertasDTO dto) {
        log.info("Salvando oferta: {}", dto);
        Ofertas ofertas = dto.toEntity();
        ofertas.setProduct(dto.getProduct());
        ofertas.setDeliverables(dto.getDeliverables());
        ofertas.setSalesStartingAt(dto.getSalesStartingAt());
        ofertas.setSalesEndingAt(dto.getSalesEndingAt());
        ofertas = ofertasRepository.save(ofertas);
        return ofertas.toDTO();
    }

    @Transactional
    public OfertasDTO update(OfertasDTO ofertasDTO, String code) {
        log.info("Atualizando oferta: {}", ofertasDTO);
        Ofertas ofertas = ofertasRepository.findById(code).orElseThrow();
        BeanUtils.copyProperties(ofertasDTO, ofertas, "id");
        ofertas = ofertasRepository.save(ofertas);
        return ofertas.toDTO();
    }

    public void delete(String code) {
        log.info("Deletando oferta: {}", code);
        var ofertas = ofertasRepository.findById(code)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Oferta não encontrada id: " + code));
        ofertasRepository.deleteById(ofertas.getCode());
    }
}

