package com.hiberus.enviadorprendas.infraestructure.kafka.service;

import com.hiberus.enviadorprendas.domain.model.Prenda;
import com.hiberus.enviadorprendas.domain.service.PrendaService;
import com.hiberus.enviadorprendas.infraestructure.kafka.avro.PrendaKey;
import com.hiberus.enviadorprendas.infraestructure.kafka.avro.PrendaValue;
import com.hiberus.enviadorprendas.infraestructure.kafka.mapper.PrendaKafkaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrendaServiceImpl implements PrendaService {

    @Value("${environment.prendas-topic}")
    private String PRENDAS_TOPIC;
    @Autowired
    PrendaKafkaMapper prendaMapper;

    @Autowired
    private KafkaTemplate<PrendaKey, PrendaValue> kafkaTemplate;

    @Override
    public void crear(Prenda prenda) {
        if(prenda == null) {
            log.error("Abortando, la prenda es nula");
            return;
        }
        PrendaKey prendaKey = new PrendaKey();
        prendaKey.setReferencia(prenda.getReferencia());
        PrendaValue prendaValue = prendaMapper.prendaToPrendaValue(prenda);

        log.debug("Enviando la prenda al topic de kafka");
        kafkaTemplate.send(PRENDAS_TOPIC, prendaKey, prendaValue);
    }

    @Override
    public void eliminar(String referencia) {
        PrendaKey prendaKey = new PrendaKey(referencia);

        log.debug("Enviando la prenda al topic de kafka");
        kafkaTemplate.send(PRENDAS_TOPIC, prendaKey, null);
    }
}
