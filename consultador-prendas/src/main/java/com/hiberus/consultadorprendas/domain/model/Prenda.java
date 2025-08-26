package com.hiberus.consultadorprendas.domain.model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public final class Prenda {
    @Id
    String referencia;

    BigDecimal precio;
    BigDecimal precioPromocionado;
    @ElementCollection(fetch = FetchType.EAGER,targetClass=String.class)
    List<String> categorias;

    public Prenda(String referencia, double precio, double precioPromocionado, List<String> categorias) {
        this.referencia = referencia;
        this.precio = BigDecimal.valueOf(precio).setScale(2, RoundingMode.UNNECESSARY);
        this.precioPromocionado =  BigDecimal.valueOf(precioPromocionado).setScale(2, RoundingMode.UNNECESSARY);
        this.categorias = categorias;
    }
}
