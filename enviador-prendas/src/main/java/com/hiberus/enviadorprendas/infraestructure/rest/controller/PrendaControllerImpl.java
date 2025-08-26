package com.hiberus.enviadorprendas.infraestructure.rest.controller;


import com.hiberus.enviadorprendas.application.CrearPrenda;
import com.hiberus.enviadorprendas.application.EliminarPrenda;
import com.hiberus.enviadorprendas.domain.model.Prenda;
import com.hiberus.enviadorprendas.infraestructure.kafka.service.PrendaServiceImpl;
import com.hiberus.enviadorprendas.infraestructure.rest.dto.PrendaDeleteRequestDto;
import com.hiberus.enviadorprendas.infraestructure.rest.dto.PrendaRequestDto;
import com.hiberus.enviadorprendas.infraestructure.rest.mapper.PrendaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class PrendaControllerImpl implements PrendaController {

    @Autowired
    PrendaMapper prendaMapper;

    @Autowired
    PrendaServiceImpl prendaService;

    @Override
    @PostMapping(value ="/prendas")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void crearPrenda(@Valid @RequestBody PrendaRequestDto prendaRequestDto) {
        log.debug("Recibida peticion de crear prenda");
        Prenda prenda = prendaMapper.prendaRequestDtoToPrenda(prendaRequestDto);
        CrearPrenda.crear(prenda, prendaService);
    }

    @Override
    @DeleteMapping(value="/prendas/{referencia}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void eliminarPrenda(@PathVariable PrendaDeleteRequestDto referencia) {
        log.debug("Recibida peticion de eliminar prenda");
        EliminarPrenda.eliminar(referencia.getReferencia(), prendaService);
    }
}
