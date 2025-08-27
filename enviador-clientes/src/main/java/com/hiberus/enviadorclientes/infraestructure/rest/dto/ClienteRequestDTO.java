package com.hiberus.enviadorclientes.infraestructure.rest.dto;

import com.hiberus.enviadorclientes.domain.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteRequestDTO {
    String referencia;
    String nombre;
    Set<Categoria> categorias;
}
