package com.alga.algarworks.repositories;


import com.alga.algarworks.entities.Produtos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

    Page<Produtos> findByIdContainingIgnoreCase(String id, Pageable pageable);

    Page<Produtos> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Produtos> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

}
