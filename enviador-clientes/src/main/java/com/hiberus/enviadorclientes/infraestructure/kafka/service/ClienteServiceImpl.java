package com.hiberus.enviadorclientes.infraestructure.kafka.service;

import com.hiberus.enviadorclientes.domain.model.Cliente;
import com.hiberus.enviadorclientes.domain.service.ClienteService;
import com.hiberus.enviadorclientes.infraestructure.kafka.avro.ClienteKey;
import com.hiberus.enviadorclientes.infraestructure.kafka.avro.ClienteValue;
import com.hiberus.enviadorclientes.infraestructure.kafka.mapper.ClienteKafkaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    @Value("${environment.clientes-topic}")
    private String CLIENTES_TOPIC;
    @Autowired
    ClienteKafkaMapper clienteMapper;

    @Autowired
    private KafkaTemplate<ClienteKey, ClienteValue> kafkaTemplate;

    @Override
    public void crear(Cliente cliente) {
        if (cliente == null){
            log.error("Abortando, el cliente es nulo");
            return;
        }
        ClienteKey clienteKey = new ClienteKey();
        clienteKey.setReferencia(cliente.getReferencia());
        ClienteValue clienteValue = clienteMapper.clienteToClienteValue(cliente);
        log.debug("Enviando el cliente al topic de kafka");
        kafkaTemplate.send(CLIENTES_TOPIC, clienteKey, clienteValue);
    }

    @Override
    public void eliminar(String referencia) {
        ClienteKey clienteKey = new ClienteKey();
        clienteKey.setReferencia(referencia);
        log.debug("Eliminando cliente del topic");
        kafkaTemplate.send(CLIENTES_TOPIC, clienteKey, null);
    }
}
