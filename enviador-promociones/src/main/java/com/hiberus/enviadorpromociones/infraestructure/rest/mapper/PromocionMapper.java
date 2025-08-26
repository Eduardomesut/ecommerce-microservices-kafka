package com.hiberus.enviadorpromociones.infraestructure.rest.mapper;

import com.hiberus.enviadorpromociones.domain.Promocion;
import com.hiberus.enviadorpromociones.infraestructure.rest.dto.PromocionRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromocionMapper {
    Promocion promocionRequestDtoToPromocion(PromocionRequestDto promocionRequestDto);
}
