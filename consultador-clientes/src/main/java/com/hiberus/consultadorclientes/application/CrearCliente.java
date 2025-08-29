package com.hiberus.consultadorclientes.application;

import com.hiberus.consultadorclientes.domain.model.Cliente;
import com.hiberus.consultadorclientes.domain.repository.ClienteRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CrearCliente {
    public static void crear(ClienteRepository clienteRepository, Cliente cliente){
        clienteRepository.save(cliente);
    }

}
