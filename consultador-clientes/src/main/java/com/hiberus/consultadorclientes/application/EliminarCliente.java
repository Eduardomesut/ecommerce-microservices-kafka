package com.hiberus.consultadorclientes.application;

import com.hiberus.consultadorclientes.domain.repository.ClienteRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EliminarCliente {

    public static void eliminar(String referencia, ClienteRepository clienteRepository){
        clienteRepository.findById(referencia)
                .ifPresent(clienteRepository::delete);
    }

}
