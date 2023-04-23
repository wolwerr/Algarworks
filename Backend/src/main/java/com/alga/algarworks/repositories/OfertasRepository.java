package com.alga.algarworks.repositories;

import com.alga.algarworks.entities.Ofertas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfertasRepository extends JpaRepository<Ofertas, String> {

    Page<Ofertas> findByCodeContainingIgnoreCase(String code, Pageable pageable);

    Page<Ofertas> findByInternalNameContainingIgnoreCase(String internalName, Pageable pageable);


    Page<Ofertas> findByActiveContainingIgnoreCase(boolean active, Pageable pageable);



}



