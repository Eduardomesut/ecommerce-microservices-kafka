package com.hiberus.aplicadorpromociones;

import com.hiberus.aplicadorpromociones.domain.model.Prenda;
import com.hiberus.aplicadorpromociones.domain.model.PrendaPromocionada;
import com.hiberus.aplicadorpromociones.domain.model.PrendaPromocionadaPkey;
import com.hiberus.aplicadorpromociones.domain.model.Promocion;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestUtils {
    public final static String NOMBRE_PROMOCION = "Black Friday 2027";

    public final static String REFERENCIA = "S123456760";
    public final static List<String> CATEGORIAS = List.of("Mujer", "Hombre");

    public static Prenda getPrenda(double precio, double precioPromocionado) {
        return Prenda.builder()
            .referencia(REFERENCIA)
            .precio(precio)
            .precio_promocionado(precioPromocionado)
            .categorias(CATEGORIAS)
            .build();
    }

    public static Promocion getPromocion(double descuento) {
        return Promocion.builder()
            .nombre(NOMBRE_PROMOCION)
            .descuento(descuento)
            .build();
    }

    public static PrendaPromocionadaPkey getPrendaPromcionadaPkey() {
        return PrendaPromocionadaPkey.builder()
            .prenda(REFERENCIA)
            .promocion(NOMBRE_PROMOCION)
            .build();
    }

    public static PrendaPromocionada getPrendaPromocionada(PrendaPromocionadaPkey id, Prenda prenda, Promocion promocion) {
        return PrendaPromocionada.builder()
            .id(id)
            .prenda(prenda)
            .promocion(promocion)
            .build();
    }
}
