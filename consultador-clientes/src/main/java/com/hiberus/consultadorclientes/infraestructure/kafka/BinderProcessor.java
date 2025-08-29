package com.hiberus.consultadorclientes.infraestructure.kafka;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface BinderProcessor {
    String CLIENTES = "clientes";

    @Input(CLIENTES)
    KStream<?,?> clientes();
}
