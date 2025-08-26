package com.hiberus.enviadorpromociones.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@NoArgsConstructor
public class Promocion {
    private final int CERO_DESCUENTO = 0;
    private final int DECIMALES_DESCUENTO_PERMITIDOS = 2;
    String nombre;
    double descuento;

    public Promocion(String nombre, double descuento) {
        validarDescuento(descuento);
        this.nombre = nombre;
        this.descuento = redondear(descuento);
    }

    private void validarDescuento(double descuento) {
        if (descuento < CERO_DESCUENTO) throw new IllegalArgumentException();
    }

    private double redondear(double precio) {
        BigDecimal precioBigDecimal = BigDecimal.valueOf(precio);
        precioBigDecimal = precioBigDecimal.setScale(DECIMALES_DESCUENTO_PERMITIDOS, RoundingMode.DOWN);
        return precioBigDecimal.doubleValue();
    }
}
