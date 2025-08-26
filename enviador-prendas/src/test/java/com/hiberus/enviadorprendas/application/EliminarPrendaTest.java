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
class EliminarPrendaTest {
    @Mock
    private PrendaServiceImpl prendaService;

    @Test
    void deberiaEliminarUnaPrenda() throws IOException {
        // Given a prenda
        Prenda prenda = getPrenda("prenda");

        // When
        prendaService.eliminar(prenda.getReferencia());

        //Then
        verify(prendaService, times(1)).eliminar(prenda.getReferencia());
    }
}

