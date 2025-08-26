package com.hiberus.aplicadorpromociones.infraestructure.kafka.service;

import com.hiberus.aplicadorpromociones.application.CrearPrenda;
import com.hiberus.aplicadorpromociones.application.EliminarPrenda;
import com.hiberus.aplicadorpromociones.domain.model.Prenda;
import com.hiberus.aplicadorpromociones.domain.repository.PrendaRepository;
import com.hiberus.aplicadorpromociones.infraestructure.kafka.BinderProcessor;
import com.hiberus.aplicadorpromociones.infraestructure.kafka.avro.PrendaPromocionadaKey;
import com.hiberus.aplicadorpromociones.infraestructure.kafka.avro.PrendaPromocionadaValue;
import com.hiberus.enviadorprendas.infraestructure.kafka.avro.PrendaKey;
import com.hiberus.enviadorprendas.infraestructure.kafka.avro.PrendaValue;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Log4j2
public class PrendasListener {

    @Autowired
    PrendaRepository prendaRepository;

    @Autowired
    PrendaServiceImpl prendaService;

    @StreamListener()
    @SendTo(BinderProcessor.PRENDAS_PROMOCIONADAS)
    public KStream<PrendaPromocionadaKey, PrendaPromocionadaValue> prendas(
        @Input(BinderProcessor.PRENDAS) KStream<PrendaKey, PrendaValue> prendas) {

        log.debug("Prenda recibida por topid de kafka");
        return prendas.flatMap((prendaKey, prendaValue) -> {
            log.debug("PrendaKey {}, PrendaValue {}", prendaKey, prendaValue);
            List<KeyValue<PrendaPromocionadaKey, PrendaPromocionadaValue>> result = new LinkedList<>();
            if ((prendaValue == null)) { // Thombstone record
                EliminarPrenda.eliminar(prendaKey.getReferencia(), prendaRepository, prendaService);
                result.add(KeyValue.pair(new PrendaPromocionadaKey(prendaKey.getReferencia()), null));
            } else {
                log.debug("Prenda is not a tombstone");
                Prenda prenda = new Prenda(prendaValue.getReferencia(), prendaValue.getPrecio(),
                    prendaValue.getPrecio(), prendaValue.getCategorias());
                CrearPrenda.crear(prenda, prendaRepository, prendaService);
                PrendaPromocionadaValue prendaPromocionadaValue = new PrendaPromocionadaValue(
                    prenda.getReferencia(),
                    prenda.getPrecio(),
                    prenda.getPrecio_promocionado(),
                    prenda.getCategorias()
                );
                result.add(
                    KeyValue.pair(new PrendaPromocionadaKey(prendaKey.getReferencia()), prendaPromocionadaValue));
            }
            return result;
        });
    }
}
