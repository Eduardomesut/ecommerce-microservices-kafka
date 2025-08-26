package com.hiberus.consultadorprendas.domain.repository;

import com.hiberus.consultadorprendas.domain.model.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrendaRepository extends JpaRepository<Prenda, String> {
}
