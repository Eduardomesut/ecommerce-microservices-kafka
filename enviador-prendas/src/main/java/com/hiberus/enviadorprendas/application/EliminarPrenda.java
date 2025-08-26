package com.hiberus.enviadorprendas.application;

import com.hiberus.enviadorprendas.domain.model.Prenda;
import com.hiberus.enviadorprendas.domain.service.PrendaService;

public final class EliminarPrenda {
    private EliminarPrenda(){}
    public static void eliminar(String referencia, PrendaService prendaService) {
        prendaService.eliminar(referencia);
    }
}
