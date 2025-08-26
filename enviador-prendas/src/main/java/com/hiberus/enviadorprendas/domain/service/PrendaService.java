package com.hiberus.enviadorprendas.domain.service;

import com.hiberus.enviadorprendas.domain.model.Prenda;

public interface PrendaService {
    void crear(Prenda prenda);
    void eliminar(String referencia);
}
