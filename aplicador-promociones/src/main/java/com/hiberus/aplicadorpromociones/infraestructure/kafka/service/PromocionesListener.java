package com.hiberus.aplicadorpromociones.infraestructure.kafka.service;

import com.hiberus.aplicadorpromociones.application.CrearPromocion;
import com.hiberus.aplicadorpromociones.application.EliminarPromocion;
import com.hiberus.aplicadorpromociones.domain.model.Promocion;
import com.hiberus.aplicadorpromociones.domain.repository.PrendaPromocionadaRepository;
import com.hiberus.aplicadorpromociones.domain.repository.PrendaRepository;
import com.hiberus.aplicadorpromociones.domain.repository.PromocionRepository;
import com.hiberus.aplicadorpromociones.domain.service.PrendaService;
import com.hiberus.aplicadorpromociones.infraestructure.kafka.BinderProcessor;
import com.hiberus.enviadorpromociones.infraestructure.kafka.avro.PromocionKey;
import com.hiberus.enviadorpromociones.infraestructure.kafka.avro.PromocionValue;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@Log4j2
public class PromocionesListener {

    @Autowired
    PromocionRepository promocionRepository;

    @Autowired
    PrendaRepository prendaRepository;

    @Autowired
    PrendaPromocionadaRepository prendaPromocionadaRepository;

    @Autowired
    PrendaService prendaService;

    @StreamListener
    @Profile({"default"})
    public void promociones(@Input(BinderProcessor.PROMOCIONES) KStream<PromocionKey, PromocionValue> promociones) {

        log.debug("Promocion recibida por topid de kafka");
        promociones.foreach((promocionKey, promocionValue) -> {

            log.debug("promocionKey {}, promocionValue {}", promocionKey, promocionValue);
            if ((promocionValue == null)) { // Thombstone record
                EliminarPromocion.eliminar(promocionKey.getNombre(), promocionRepository, prendaRepository,
                    prendaPromocionadaRepository, prendaService);
                return;
            }
            Promocion promocion = new Promocion(promocionValue.getNombre(), promocionValue.getDescuento(),
                new ArrayList<>());
            CrearPromocion.crear(promocion, promocionRepository);
        });
    }
}
