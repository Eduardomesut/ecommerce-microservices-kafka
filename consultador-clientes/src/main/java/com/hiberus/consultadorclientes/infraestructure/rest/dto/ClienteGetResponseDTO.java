package com.hiberus.consultadorclientes.infraestructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ClienteGetResponseDTO {
    String referencia;
    String nombre;
    List<String> categorias;

}
