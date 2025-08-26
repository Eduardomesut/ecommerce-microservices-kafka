package com.hiberus.aplicadorpromociones.infraestructure.kafka.service;

import com.hiberus.aplicadorpromociones.domain.model.Prenda;
import com.hiberus.aplicadorpromociones.domain.service.PrendaService;
import com.hiberus.aplicadorpromociones.infraestructure.kafka.avro.PrendaPromocionadaKey;
import com.hiberus.aplicadorpromociones.infraestructure.kafka.avro.PrendaPromocionadaValue;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PrendaServiceImpl implements PrendaService {

    final String PRENDAS_PROMOCIONADAS_TOPIC = "prendas_promocionadas";

    @Autowired
    private KafkaTemplate<PrendaPromocionadaKey, PrendaPromocionadaValue> kafkaTemplate;

    @Override
    public void crear(Prenda prenda) {
        PrendaPromocionadaKey prendaPromocionadaKey = new PrendaPromocionadaKey();
        prendaPromocionadaKey.setReferencia(prenda.getReferencia());

        PrendaPromocionadaValue prendaPromocionadaValue = new PrendaPromocionadaValue(
          prenda.getReferencia(),
          prenda.getPrecio(),
          prenda.getPrecio_promocionado(),
          prenda.getCategorias()
        );
        kafkaTemplate.send(PRENDAS_PROMOCIONADAS_TOPIC, prendaPromocionadaKey, prendaPromocionadaValue);
    }

    @Override
    public void eliminar(String referencia) {
        PrendaPromocionadaKey prendaPromocionadaKey = new PrendaPromocionadaKey();
        prendaPromocionadaKey.setReferencia(referencia);
        kafkaTemplate.send(PRENDAS_PROMOCIONADAS_TOPIC, prendaPromocionadaKey, null);
    }

    public void crear(List<Prenda> prendas) {
        for(Prenda prenda: prendas) {
            crear(prenda);
        }
    }
}
