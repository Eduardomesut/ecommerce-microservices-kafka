package com.hiberus.consultadorprendas.infraestructure.rest.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PrendaGetResponseDto {
    String referencia;
    String precio;
    String precio_promocionado;
    List<String> categorias;
}
