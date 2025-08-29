package com.hiberus.consultadorclientes;

import com.hiberus.consultadorclientes.infraestructure.kafka.BinderProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(BinderProcessor.class)
public class ConsultadorClientesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultadorClientesApplication.class, args);
    }

}
