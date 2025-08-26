package com.hiberus.enviadorpromociones.infraestructure.rest.controller;

import com.hiberus.enviadorpromociones.application.CrearPromocion;
import com.hiberus.enviadorpromociones.application.EliminarPromocion;
import com.hiberus.enviadorpromociones.domain.Promocion;
import com.hiberus.enviadorpromociones.infraestructure.rest.dto.PromocionDeleteRequestDto;
import com.hiberus.enviadorpromociones.infraestructure.rest.dto.PromocionRequestDto;
import com.hiberus.enviadorpromociones.infraestructure.kafka.service.service.PromocionServiceImpl;
import com.hiberus.enviadorpromociones.infraestructure.rest.mapper.PromocionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PromocionControllerImpl implements PromocionController {

    @Autowired
    PromocionServiceImpl promocionService;

    @Autowired
    PromocionMapper promocionMapper;

    @Override
    @PostMapping(value ="/promociones")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void crearPromocion(@Valid @RequestBody PromocionRequestDto promocionDto) {
        Promocion promocion = promocionMapper.promocionRequestDtoToPromocion(promocionDto);
        CrearPromocion.crear(promocion, promocionService);
    }

    @Override
    @DeleteMapping(value="/promociones/{nombre}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void eliminarPromocion(@PathVariable PromocionDeleteRequestDto nombre) {
        EliminarPromocion.eliminar(nombre.getNombre(), promocionService);
    }
}
