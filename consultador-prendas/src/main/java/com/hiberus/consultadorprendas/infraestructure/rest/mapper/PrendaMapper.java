package com.hiberus.consultadorprendas.infraestructure.rest.mapper;

import com.hiberus.consultadorprendas.domain.model.Prenda;
import com.hiberus.consultadorprendas.infraestructure.rest.dto.PrendaGetResponseDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrendaMapper {

    @Mapping(source = "precioPromocionado", target = "precio_promocionado")
    PrendaGetResponseDto prendaToPrendaGetResponseDto(Prenda prenda);

    List<PrendaGetResponseDto> prendaListToPrendaGetResponseDtoList(List<Prenda> prendas);

}
