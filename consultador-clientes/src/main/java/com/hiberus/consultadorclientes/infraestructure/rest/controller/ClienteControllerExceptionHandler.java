package com.hiberus.consultadorclientes.infraestructure.rest.controller;

import com.hiberus.consultadorclientes.domain.exceptions.ClienteNoExiste;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClienteControllerExceptionHandler {
    @ExceptionHandler(value = {ClienteNoExiste.class})
    public ResponseEntity<?> handleClienteNoExiste(ClienteNoExiste ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
