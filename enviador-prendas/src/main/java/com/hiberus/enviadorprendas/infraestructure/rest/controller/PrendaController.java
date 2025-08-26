package com.hiberus.enviadorprendas.infraestructure.rest.controller;

import com.hiberus.enviadorprendas.infraestructure.rest.dto.PrendaDeleteRequestDto;
import com.hiberus.enviadorprendas.infraestructure.rest.dto.PrendaRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface PrendaController {
    @Operation(summary = "Crear una prenda")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Peticion aceptada")
    })
    void crearPrenda(PrendaRequestDto prendaRequestDto);

    @Operation(summary = "Eliminar una prenda")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Peticion aceptada")
    })
    void eliminarPrenda(PrendaDeleteRequestDto prendaDeleteRequestDto);
}
