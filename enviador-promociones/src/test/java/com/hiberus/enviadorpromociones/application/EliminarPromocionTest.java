package com.hiberus.enviadorpromociones.application;


import static com.hiberus.enviadorpromociones.TestUtils.getPromocion;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.hiberus.enviadorpromociones.domain.Promocion;
import com.hiberus.enviadorpromociones.infraestructure.kafka.service.service.PromocionServiceImpl;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
class EliminarPromocionTest {
    @Mock
    private PromocionServiceImpl promocionService;


    @Test
    void deberiaEliminarUnaPromocion() throws IOException {
        // Given a promotion
        Promocion promocion = getPromocion("promocion");

        // When is eliminated
        EliminarPromocion.eliminar(promocion.getNombre(), promocionService);

        //Then assert that eliminate method is called
        verify(promocionService, times(1)).eliminar(promocion.getNombre());
    }
}

