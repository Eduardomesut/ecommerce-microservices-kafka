package com.hiberus.aplicadorpromociones.infraestructure.rest.controller;

import com.hiberus.aplicadorpromociones.domain.exceptions.PrendaNoExiste;
import com.hiberus.aplicadorpromociones.domain.exceptions.PromocionNoExiste;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface PrendaPromocionadaController {
    @Operation(summary = "Aplicar una promoción para una prenda")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Peticion aceptada")
    })
    void aplicarPromocion(String prenda, String promocion) throws PromocionNoExiste, PrendaNoExiste;

    @Operation(summary = "Desaplicar una promoción para una prenda")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Peticion aceptada")
    })
    void desaplicarPromocion(String prenda, String promocion) throws PromocionNoExiste, PrendaNoExiste;

}
