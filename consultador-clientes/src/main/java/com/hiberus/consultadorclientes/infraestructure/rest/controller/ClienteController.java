package com.hiberus.consultadorclientes.infraestructure.rest.controller;

import com.hiberus.consultadorclientes.domain.exceptions.ClienteNoExiste;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface ClienteController {

    @Operation(summary = "Listar todos los clientes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Clientes listados")
    })
    ResponseEntity<Object> listarTodosLosClientes();

    @Operation(summary = "Listar un cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente listado")
    })
    ResponseEntity<Object> listarClientes(String referencia) throws ClienteNoExiste;
}
