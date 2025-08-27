package com.hiberus.enviadorclientes.infraestructure.kafka.mapper;

import com.hiberus.enviadorclientes.domain.model.Cliente;
import com.hiberus.enviadorclientes.infraestructure.kafka.avro.ClienteValue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteKafkaMapper {
    ClienteValue clienteToClienteValue(Cliente cliente);
}
