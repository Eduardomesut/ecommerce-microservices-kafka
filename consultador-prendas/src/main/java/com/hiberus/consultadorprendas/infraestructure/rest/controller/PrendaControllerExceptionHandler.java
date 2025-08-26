package com.hiberus.consultadorprendas.infraestructure.rest.controller;

import com.hiberus.consultadorprendas.domain.exceptions.PrendaNoExiste;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PrendaControllerExceptionHandler {
    @ExceptionHandler(value = {PrendaNoExiste.class})
    public ResponseEntity<?>  handlePrendaNoExiste(PrendaNoExiste ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
