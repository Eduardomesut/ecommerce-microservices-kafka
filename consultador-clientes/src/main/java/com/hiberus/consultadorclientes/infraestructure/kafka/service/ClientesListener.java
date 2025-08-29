package com.hiberus.consultadorclientes.infraestructure.kafka.service;

import com.hiberus.consultadorclientes.application.CrearCliente;
import com.hiberus.consultadorclientes.application.EliminarCliente;
import com.hiberus.consultadorclientes.domain.model.Cliente;
import com.hiberus.consultadorclientes.domain.repository.ClienteRepository;
import com.hiberus.consultadorclientes.infraestructure.kafka.BinderProcessor;
import com.hiberus.enviadorclientes.infraestructure.kafka.avro.ClienteKey;
import com.hiberus.enviadorclientes.infraestructure.kafka.avro.ClienteValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class ClientesListener {

    @Autowired
    ClienteRepository clienteRepository;

    @StreamListener
    public void clientes (@Input(BinderProcessor.CLIENTES) KStream<ClienteKey, ClienteValue> clientes){
        clientes.foreach((clienteKey, clienteValue) -> {
            if ((clienteValue == null)){
                EliminarCliente.eliminar(clienteKey.getReferencia(), clienteRepository);
                return;
            }

            Cliente cliente = new Cliente(
                    clienteValue.getReferencia(),
                    clienteValue.getNombre(),
                    clienteValue.getCategorias());
            CrearCliente.crear(clienteRepository, cliente);
        });


    }
}
