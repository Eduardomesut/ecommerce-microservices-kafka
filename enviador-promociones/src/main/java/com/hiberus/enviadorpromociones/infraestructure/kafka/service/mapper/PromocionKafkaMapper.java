package com.hiberus.enviadorpromociones.infraestructure.kafka.service.mapper;

import com.hiberus.enviadorpromociones.domain.Promocion;
import com.hiberus.enviadorpromociones.infraestructure.kafka.avro.PromocionValue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromocionKafkaMapper {
    PromocionValue promocionToPromocionValue(Promocion promocion);
}
