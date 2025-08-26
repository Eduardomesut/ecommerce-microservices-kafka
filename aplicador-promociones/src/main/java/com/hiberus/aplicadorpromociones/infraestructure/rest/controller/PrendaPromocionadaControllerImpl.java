package com.hiberus.aplicadorpromociones.infraestructure.rest.controller;


import com.hiberus.aplicadorpromociones.application.AplicarPromocion;
import com.hiberus.aplicadorpromociones.application.DesaplicarPromocion;
import com.hiberus.aplicadorpromociones.domain.exceptions.PrendaNoExiste;
import com.hiberus.aplicadorpromociones.domain.exceptions.PromocionNoExiste;
import com.hiberus.aplicadorpromociones.domain.model.PrendaPromocionadaPkey;
import com.hiberus.aplicadorpromociones.domain.repository.PrendaPromocionadaRepository;
import com.hiberus.aplicadorpromociones.domain.repository.PrendaRepository;
import com.hiberus.aplicadorpromociones.domain.repository.PromocionRepository;
import com.hiberus.aplicadorpromociones.infraestructure.kafka.service.PrendaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrendaPromocionadaControllerImpl implements PrendaPromocionadaController {

    @Autowired
    PrendaRepository prendaRepository;

    @Autowired
    PromocionRepository promocionRepository;

    @Autowired
    PrendaPromocionadaRepository prendaPromocionadaRepository;

    @Autowired
    PrendaServiceImpl prendaService;


    @Override
    @PutMapping(value="/promociones/aplicar")
    @ResponseStatus(HttpStatus.OK)
    public void aplicarPromocion(@RequestParam String prenda, @RequestParam String promocion) throws PromocionNoExiste, PrendaNoExiste {
        PrendaPromocionadaPkey prendaPromocionadaPkey = new PrendaPromocionadaPkey(prenda, promocion);
        AplicarPromocion.aplicar(prendaPromocionadaPkey, prendaRepository, promocionRepository, prendaPromocionadaRepository, prendaService);
    }

    @Override
    @PutMapping(value="/promociones/desaplicar")
    @ResponseStatus(HttpStatus.OK)
    public void desaplicarPromocion(String prenda, String promocion) throws PromocionNoExiste, PrendaNoExiste {
        PrendaPromocionadaPkey prendaPromocionadaPkey = new PrendaPromocionadaPkey(prenda, promocion);
        DesaplicarPromocion.desaplicar(prendaPromocionadaPkey, prendaRepository, promocionRepository, prendaPromocionadaRepository, prendaService);
    }
}
