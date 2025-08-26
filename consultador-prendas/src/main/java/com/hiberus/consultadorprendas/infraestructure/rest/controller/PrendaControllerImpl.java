package com.hiberus.consultadorprendas.infraestructure.rest.controller;


import com.hiberus.consultadorprendas.application.ConsultarPrenda;
import com.hiberus.consultadorprendas.domain.exceptions.PrendaNoExiste;
import com.hiberus.consultadorprendas.domain.repository.PrendaRepository;
import com.hiberus.consultadorprendas.application.ConsultarTodasLasPrendas;
import com.hiberus.consultadorprendas.domain.model.Prenda;
import com.hiberus.consultadorprendas.infraestructure.rest.dto.PrendaGetResponseDto;
import com.hiberus.consultadorprendas.infraestructure.rest.mapper.PrendaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrendaControllerImpl implements PrendaController {

    @Autowired
    PrendaRepository prendaRepository;

    @Autowired
    PrendaMapper prendaMapper;

    @Override
    @GetMapping(value="/prendas")
    public ResponseEntity<Object> listarTodasLasPrendas() {
        List<Prenda> prendas = ConsultarTodasLasPrendas.consultar(prendaRepository);
        List<PrendaGetResponseDto> prendaGetResponseDto = prendaMapper.prendaListToPrendaGetResponseDtoList(prendas);
        return ResponseEntity.status(HttpStatus.OK).body(prendaGetResponseDto);
    }

    @Override
    @GetMapping(value="/prendas/{referencia}")
    public ResponseEntity<Object> listarPrendas(@PathVariable String referencia) throws PrendaNoExiste {
        Prenda prenda = ConsultarPrenda.consultar(referencia, prendaRepository);
        PrendaGetResponseDto prendaGetResponseDto = prendaMapper.prendaToPrendaGetResponseDto(prenda);
        return ResponseEntity.status(HttpStatus.OK).body(prendaGetResponseDto);
    }
}
