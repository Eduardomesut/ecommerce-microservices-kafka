package com.hiberus.consultadorclientes.domain.repository;

import com.hiberus.consultadorclientes.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
