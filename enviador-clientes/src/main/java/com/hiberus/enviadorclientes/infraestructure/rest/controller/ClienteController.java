package com.hiberus.enviadorclientes.infraestructure.rest.controller;

import com.hiberus.enviadorclientes.infraestructure.rest.dto.ClienteDeleteRequestDTO;
import com.hiberus.enviadorclientes.infraestructure.rest.dto.ClienteRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ClienteController {
    @Operation(summary = "Crear un cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Peticion aceptada")
    })
    void crearCliente(ClienteRequestDTO clienteRequestDto);

    @Operation(summary = "Eliminar un cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Peticion aceptada")
    })
    void eliminarCliente(ClienteDeleteRequestDTO clienteDeleteRequestDTO);
}
