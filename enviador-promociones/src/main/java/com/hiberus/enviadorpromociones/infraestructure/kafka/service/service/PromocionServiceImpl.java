package com.hiberus.enviadorpromociones.infraestructure.kafka.service.service;

import com.hiberus.enviadorpromociones.domain.Promocion;
import com.hiberus.enviadorpromociones.domain.PromocionService;
import com.hiberus.enviadorpromociones.infraestructure.kafka.avro.PromocionKey;
import com.hiberus.enviadorpromociones.infraestructure.kafka.avro.PromocionValue;
import com.hiberus.enviadorpromociones.infraestructure.kafka.service.mapper.PromocionKafkaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PromocionServiceImpl implements PromocionService {

    final String PROMOCIONES_TOPIC = "promociones";
    @Autowired
    PromocionKafkaMapper promocionKafkaMapper;

    @Autowired
    private KafkaTemplate<PromocionKey, PromocionValue> kafkaTemplate;
    @Override
    public void crear(Promocion promocion) {
        PromocionKey promocionKey = new PromocionKey();
        promocionKey.setNombre(promocion.getNombre());

        PromocionValue promocionValue = promocionKafkaMapper.promocionToPromocionValue(promocion);
        kafkaTemplate.send(PROMOCIONES_TOPIC, promocionKey, promocionValue);
    }

    @Override
    public void eliminar(String nombre) {
        PromocionKey promocionKey = new PromocionKey(nombre);
        kafkaTemplate.send(PROMOCIONES_TOPIC, promocionKey, null);
    }
}
