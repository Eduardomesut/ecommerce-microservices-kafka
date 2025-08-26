package com.hiberus.enviadorpromociones.application;

import com.hiberus.enviadorpromociones.domain.Promocion;
import com.hiberus.enviadorpromociones.domain.PromocionService;

public final class CrearPromocion {
    private CrearPromocion() {}

    public static void crear(Promocion promocion, PromocionService promocionService) {
        promocionService.crear(promocion);
    }
}
