package com.hiberus.aplicadorpromociones.application;

import static com.hiberus.aplicadorpromociones.TestUtils.NOMBRE_PROMOCION;
import static com.hiberus.aplicadorpromociones.TestUtils.getPromocion;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hiberus.aplicadorpromociones.domain.model.Promocion;
import com.hiberus.aplicadorpromociones.domain.repository.PromocionRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CrearPromocionTest {

    @Mock
    PromocionRepository promocionRepository;

    @Test
    void deberiaCrearUnaPromocion() {
        // Given
        Promocion promocion = getPromocion(2.00);
        when(promocionRepository.findById(NOMBRE_PROMOCION)).thenReturn(Optional.of(promocion));

        // When
        CrearPromocion.crear(promocion, promocionRepository);

        // Then
        verify(promocionRepository, times(1)).save(promocion);
    }
}
