package com.alga.algarworks.repositories;

import com.alga.algarworks.entities.Cursos;
import com.alga.algarworks.entities.Ofertas;
import com.alga.algarworks.entities.Produtos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;


public interface OfertasRepository extends JpaRepository<Ofertas, String> {

    Page<Ofertas> findByCodeContainingIgnoreCase(String code, Pageable pageable);

    Page<Ofertas> findByInternalNameContainingIgnoreCase(String internalName, Pageable pageable);


    Page<Ofertas> findByActiveContainingIgnoreCase(boolean active, Pageable pageable);


    Page<Ofertas> findBySalesStartingAtContainingIgnoreCase(Date salesStartingAt, Pageable pageable);

    Page<Ofertas> findBySalesEndingAtContainingIgnoreCase(Date salesEndingAt, Pageable pageable);


    Page<Ofertas> findBySupportDurationInDaysContainingIgnoreCase(String supportDurationInDays, Pageable pageable);


}



