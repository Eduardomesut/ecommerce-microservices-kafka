package com.hiberus.enviadorpromociones.application;

import com.hiberus.enviadorpromociones.domain.PromocionService;

public final class EliminarPromocion {
    private EliminarPromocion() {}

    public static void eliminar(String nombre, PromocionService promocionService) {
        promocionService.eliminar(nombre);
    }
}
