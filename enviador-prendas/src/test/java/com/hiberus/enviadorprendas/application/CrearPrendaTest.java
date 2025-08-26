package com.hiberus.enviadorprendas.application;


import static com.hiberus.enviadorprendas.TestUtils.getPrenda;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.hiberus.enviadorprendas.domain.model.Prenda;
import com.hiberus.enviadorprendas.infraestructure.kafka.service.PrendaServiceImpl;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
class CrearPrendaTest {
    @Mock
    private PrendaServiceImpl prendaService;

    @Test
    void deberiaCrearUnaPrenda() throws IOException {
        // Given
        Prenda prenda = getPrenda("prenda");

        // When
        prendaService.crear(prenda);

        //Then
        verify(prendaService, times(1)).crear(prenda);
    }
}

