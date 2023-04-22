package com.alga.algarworks.repositories;

import com.alga.algarworks.entities.Cursos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosRepository extends JpaRepository<Cursos, Long> {

    Page<Cursos> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Cursos> findByIdContainingIgnoreCase(String texto, Pageable pageable);
}
