package com.hiberus.consultadorprendas.infraestructure.rest.controller;

import com.hiberus.consultadorprendas.domain.exceptions.PrendaNoExiste;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface PrendaController {
    @Operation(summary = "Listar todas las prendas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prendas listadas")
    })
    ResponseEntity<Object> listarTodasLasPrendas();

    @Operation(summary = "Listar una prenda")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prenda listada")
    })
    ResponseEntity<Object> listarPrendas(String referencia) throws PrendaNoExiste;


}
