package com.hiberus.enviadorclientes.infraestructure.rest.controller;

import com.hiberus.enviadorclientes.application.CrearCliente;
import com.hiberus.enviadorclientes.application.EliminarCliente;
import com.hiberus.enviadorclientes.domain.model.Cliente;
import com.hiberus.enviadorclientes.infraestructure.kafka.service.ClienteServiceImpl;
import com.hiberus.enviadorclientes.infraestructure.rest.dto.ClienteDeleteRequestDTO;
import com.hiberus.enviadorclientes.infraestructure.rest.dto.ClienteRequestDTO;
import com.hiberus.enviadorclientes.infraestructure.rest.mapper.ClienteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class ClienteControllerImpl implements ClienteController{
    @Autowired
    ClienteMapper clienteMapper;
    @Autowired
    ClienteServiceImpl clienteService;
    @Override
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void crearCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDto) {
        log.debug("Recibida petición de crear cliente");
        Cliente cliente = clienteMapper.clienteRequestDtoToCliente(clienteRequestDto);
        CrearCliente.crear(cliente, clienteService);
    }

    @Override
    @DeleteMapping("clientes/{referencia}")
    public void eliminarCliente(@PathVariable ClienteDeleteRequestDTO referencia) {
        log.debug("Recibida petición para eliminar cliente");
        EliminarCliente.eliminar(referencia.getReferencia(), clienteService);

    }
}
