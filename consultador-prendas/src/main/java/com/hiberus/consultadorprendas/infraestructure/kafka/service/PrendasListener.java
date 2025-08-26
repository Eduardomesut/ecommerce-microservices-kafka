package com.hiberus.consultadorprendas.infraestructure.kafka.service;
import com.hiberus.aplicadorpromociones.infraestructure.kafka.avro.PrendaPromocionadaKey;
import com.hiberus.aplicadorpromociones.infraestructure.kafka.avro.PrendaPromocionadaValue;
import com.hiberus.consultadorprendas.application.CrearPrenda;
import com.hiberus.consultadorprendas.application.EliminarPrenda;
import com.hiberus.consultadorprendas.domain.model.Prenda;
import com.hiberus.consultadorprendas.domain.repository.PrendaRepository;
import com.hiberus.consultadorprendas.infraestructure.kafka.BinderProcessor;

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
public class PrendasListener {

    @Autowired
    PrendaRepository prendaRepository;

    @StreamListener
    public void prendasPromocionadas(@Input(BinderProcessor.PRENDAS_PROMOCIONADAS) KStream<PrendaPromocionadaKey, PrendaPromocionadaValue> prendas) {
            prendas.foreach((prendaPromocionadaKey, prendaPromocionadaValue) -> {
                        if ((prendaPromocionadaValue == null)) {
                            // Is a tombstone, so record must be deleted from database
                            EliminarPrenda.eliminar(prendaPromocionadaKey.getReferencia(), prendaRepository);
                            return;
                        }

                        // Create prenda in database
                        Prenda prenda = new Prenda(
                            prendaPromocionadaValue.getReferencia(),
                            prendaPromocionadaValue.getPrecio(),
                            prendaPromocionadaValue.getPrecioPromocionado(),
                            prendaPromocionadaValue.getCategorias());
                        CrearPrenda.crear(prenda, prendaRepository);
            });
    }
}
