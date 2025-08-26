package com.hiberus.aplicadorpromociones.infraestructure.rest.controller;

import com.hiberus.aplicadorpromociones.domain.exceptions.PrendaNoExiste;
import com.hiberus.aplicadorpromociones.domain.exceptions.PromocionNoExiste;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PrendaPromocionadaControllerExceptionHandler {
    @ExceptionHandler(value = {PrendaNoExiste.class})
    public ResponseEntity<?>  gestionarExcepcionPrendaNoExiste(PrendaNoExiste ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prenda no existe");
    }

    @ExceptionHandler(value = {PromocionNoExiste.class})
    public ResponseEntity<?>  gestionarExcepcionPromocionNoExiste(PromocionNoExiste ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Promocion no existe");
    }
}
