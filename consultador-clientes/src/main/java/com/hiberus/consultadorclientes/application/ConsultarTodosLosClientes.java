package com.hiberus.consultadorclientes.application;

import com.hiberus.consultadorclientes.domain.model.Cliente;
import com.hiberus.consultadorclientes.domain.repository.ClienteRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConsultarTodosLosClientes {

    public static List<Cliente> consultar(ClienteRepository clienteRepository){
        return clienteRepository.findAll();
    }
}
