package com.hiberus.consultadorclientes.infraestructure.rest.controller;

import com.hiberus.consultadorclientes.application.ConsultarCliente;
import com.hiberus.consultadorclientes.application.ConsultarTodosLosClientes;
import com.hiberus.consultadorclientes.domain.exceptions.ClienteNoExiste;
import com.hiberus.consultadorclientes.domain.model.Cliente;
import com.hiberus.consultadorclientes.domain.repository.ClienteRepository;
import com.hiberus.consultadorclientes.infraestructure.rest.dto.ClienteGetResponseDTO;
import com.hiberus.consultadorclientes.infraestructure.rest.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteControllerImpl implements ClienteController{

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteMapper clienteMapper;

    @Override
    @GetMapping(value = "/clientes")
    public ResponseEntity<Object> listarTodosLosClientes() {
        List<Cliente> clientes = ConsultarTodosLosClientes.consultar(clienteRepository);
        List<ClienteGetResponseDTO> clienteGetResponseDTOS = clienteMapper.clienteListToClienteGetResponseDtoList(clientes);

        return ResponseEntity.status(HttpStatus.OK).body(clienteGetResponseDTOS);
    }

    @Override
    @GetMapping(value = "/clientes/{referencia}")
    public ResponseEntity<Object> listarClientes(@PathVariable String referencia) throws ClienteNoExiste {
        Cliente cliente = ConsultarCliente.consultar(referencia, clienteRepository);
        ClienteGetResponseDTO clienteGetResponseDTO = clienteMapper.clienteToClienteGetResponseDto(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteGetResponseDTO);
    }
}
