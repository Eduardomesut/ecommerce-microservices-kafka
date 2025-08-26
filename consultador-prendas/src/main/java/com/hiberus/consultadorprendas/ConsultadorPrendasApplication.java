package com.hiberus.consultadorprendas;

import com.hiberus.consultadorprendas.infraestructure.kafka.BinderProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(BinderProcessor.class)
public class ConsultadorPrendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultadorPrendasApplication.class, args);
	}

}
