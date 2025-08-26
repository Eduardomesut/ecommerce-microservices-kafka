package com.hiberus.enviadorprendas.infraestructure.kafka.mapper;

import com.hiberus.enviadorprendas.domain.model.Prenda;
import com.hiberus.enviadorprendas.infraestructure.kafka.avro.PrendaValue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrendaKafkaMapper {
    PrendaValue prendaToPrendaValue(Prenda prenda);
}
