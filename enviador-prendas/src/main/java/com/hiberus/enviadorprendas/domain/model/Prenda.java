package com.hiberus.enviadorprendas.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public final class Prenda {

    private final int DECIMALES_PRECIO_PERMITIDOS = 2;

    private final String FORMATO_REFERNCIA_PERMITIDO = "^[SML][a-zA-Z0-9]{9}";

    private final int CERO_PRECIO = 0;

    String referencia;
    double precio;

    List<Categoria> categorias;

    public Prenda(String referencia, double precio, List<Categoria> categorias) {
        validarReferencia(referencia);
        validarPrecio(precio);
        this.precio = redondear(precio);
        this.referencia = referencia;
        this.categorias = categorias;
    }

    private void validarReferencia(String referencia) {
        if(!referencia.matches(FORMATO_REFERNCIA_PERMITIDO)) throw new IllegalArgumentException();
    }

    private void validarPrecio(double precio) {
        if(precio < CERO_PRECIO) throw new IllegalArgumentException();
    }

    private double redondear(double precio) {
        BigDecimal precioBigDecimal = BigDecimal.valueOf(precio);
        precioBigDecimal = precioBigDecimal.setScale(DECIMALES_PRECIO_PERMITIDOS, RoundingMode.DOWN);
        return precioBigDecimal.doubleValue();
    }
}