package com.hiberus.enviadorclientes.application;

import com.hiberus.enviadorclientes.domain.model.Cliente;
import com.hiberus.enviadorclientes.domain.service.ClienteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class CrearCliente {
    private CrearCliente(){}
    public static void crear(Cliente cliente, ClienteService clienteService){
        if (cliente == null){
            log.error("El cliente no puede ser nulo");
            return;
        }

        log.debug("Enviando cliente {} a topic de kafka", cliente.getReferencia());
        clienteService.crear(cliente);
    }

}
