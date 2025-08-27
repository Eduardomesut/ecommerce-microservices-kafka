package com.hiberus.enviadorclientes.domain.service;

import com.hiberus.enviadorclientes.domain.model.Cliente;

public interface ClienteService {

    void crear(Cliente cliente);
    void eliminar(String referencia);
}
