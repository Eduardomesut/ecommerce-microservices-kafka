package com.hiberus.consultadorclientes.application;

import com.hiberus.consultadorclientes.domain.exceptions.ClienteNoExiste;
import com.hiberus.consultadorclientes.domain.model.Cliente;
import com.hiberus.consultadorclientes.domain.repository.ClienteRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConsultarCliente {

    public static Cliente consultar(String referencia, ClienteRepository clienteRepository)throws ClienteNoExiste{
        return clienteRepository
                .findById(referencia)
                .orElseThrow(ClienteNoExiste::new);

    }


}
