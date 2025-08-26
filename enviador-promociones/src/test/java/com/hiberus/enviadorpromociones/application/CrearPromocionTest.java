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
class CrearPromocionTest {
    @Mock
    private PromocionServiceImpl promocionService;

    @Test
    void deberiaCrearUnaPromocion() throws IOException {
        // Given a promotion
        Promocion promocion = getPromocion("promocion");

        // When is created
        CrearPromocion.crear(promocion, promocionService);

        //Then assert that create method is called
        verify(promocionService, times(1)).crear(promocion);
    }
}

