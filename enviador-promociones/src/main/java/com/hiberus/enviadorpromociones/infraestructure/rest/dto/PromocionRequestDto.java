package com.hiberus.enviadorpromociones.infraestructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PromocionRequestDto {

    String nombre;

    @DecimalMin(value = "0.0")
    BigDecimal descuento;
}
