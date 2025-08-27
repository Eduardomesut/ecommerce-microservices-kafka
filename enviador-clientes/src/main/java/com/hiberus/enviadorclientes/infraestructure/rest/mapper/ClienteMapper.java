package com.hiberus.enviadorclientes.infraestructure.rest.mapper;

import com.hiberus.enviadorclientes.domain.model.Cliente;
import com.hiberus.enviadorclientes.infraestructure.rest.dto.ClienteRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente clienteRequestDtoToCliente(ClienteRequestDTO clienteRequestDTO);
}
