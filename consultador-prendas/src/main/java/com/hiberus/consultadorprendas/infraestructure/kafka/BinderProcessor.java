package com.hiberus.consultadorprendas.infraestructure.kafka;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface BinderProcessor {
    String PRENDAS_PROMOCIONADAS = "prendas_promocionadas";

    @Input(PRENDAS_PROMOCIONADAS)
    KStream<?, ?> prendas_promocionadas();
}