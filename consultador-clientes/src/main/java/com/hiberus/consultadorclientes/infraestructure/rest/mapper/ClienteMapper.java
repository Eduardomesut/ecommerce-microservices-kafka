package com.hiberus.consultadorclientes.infraestructure.rest.mapper;

import com.hiberus.consultadorclientes.domain.model.Cliente;
import com.hiberus.consultadorclientes.infraestructure.rest.dto.ClienteGetResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteGetResponseDTO clienteToClienteGetResponseDto(Cliente cliente);

    List<ClienteGetResponseDTO> clienteListToClienteGetResponseDtoList(List<Cliente> clientes);

}
