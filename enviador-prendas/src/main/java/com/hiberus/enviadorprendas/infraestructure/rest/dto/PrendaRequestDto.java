package com.hiberus.enviadorprendas.infraestructure.rest.dto;

import com.hiberus.enviadorprendas.domain.model.Categoria;
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
public class PrendaRequestDto {
    String referencia;
    @DecimalMin(value = "0.0")
    BigDecimal precio;
    Set<Categoria> categorias;
}
