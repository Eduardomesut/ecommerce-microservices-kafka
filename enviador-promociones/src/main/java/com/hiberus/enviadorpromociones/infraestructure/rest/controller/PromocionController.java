package com.hiberus.enviadorpromociones.infraestructure.rest.controller;

import com.hiberus.enviadorpromociones.infraestructure.rest.dto.PromocionDeleteRequestDto;
import com.hiberus.enviadorpromociones.infraestructure.rest.dto.PromocionRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface PromocionController {
    @Operation(summary = "Crear una promocion")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Promocion enviada"),
            @ApiResponse(responseCode = "400", description = "Peticion incorrecta")
    })
    void crearPromocion(PromocionRequestDto promocionDto);

    @Operation(summary = "Eliminar una promocion")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Peticion enviada")
    })
    void eliminarPromocion(PromocionDeleteRequestDto prendaDeleteRequestDto);
}
