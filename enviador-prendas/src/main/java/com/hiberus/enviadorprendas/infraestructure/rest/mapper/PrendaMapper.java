package com.hiberus.enviadorprendas.infraestructure.rest.mapper;

import com.hiberus.enviadorprendas.domain.model.Prenda;
import com.hiberus.enviadorprendas.infraestructure.rest.dto.PrendaRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrendaMapper {
    Prenda prendaRequestDtoToPrenda(PrendaRequestDto prendaRequestDto);
}
