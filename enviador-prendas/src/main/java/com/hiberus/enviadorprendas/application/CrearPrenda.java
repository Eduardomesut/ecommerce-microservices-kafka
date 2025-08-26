package com.hiberus.enviadorprendas.application;

import com.hiberus.enviadorprendas.domain.model.Prenda;
import com.hiberus.enviadorprendas.domain.service.PrendaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class CrearPrenda {
    private CrearPrenda(){}
    public static void crear(Prenda prenda, PrendaService prendaService) {
        if(prenda == null) {
            log.error("Prenda no puede ser nula");
            return;
        }

        log.debug("Enviando prenda {} a topic de kafka", prenda.getReferencia());
        prendaService.crear(prenda);
    }
}
