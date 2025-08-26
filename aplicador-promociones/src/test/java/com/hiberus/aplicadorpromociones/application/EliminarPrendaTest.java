package com.hiberus.aplicadorpromociones.application;

import static com.hiberus.aplicadorpromociones.TestUtils.REFERENCIA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hiberus.aplicadorpromociones.domain.repository.PrendaRepository;
import com.hiberus.aplicadorpromociones.infraestructure.kafka.service.PrendaServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class EliminarPrendaTest {

    @Mock
    PrendaRepository prendaRepository;

    @Mock
    PrendaServiceImpl prendaService;

    @Test
    void eliminarPrenda() {
        // GIVEN una prenda
        when(prendaRepository.existsById(REFERENCIA)).thenReturn(true);

        // WHEN se elimina
        EliminarPrenda.eliminar(REFERENCIA, prendaRepository, prendaService);

        // THEN es eliminada
        verify(prendaRepository, times(1)).existsById(REFERENCIA);
        verify(prendaRepository, times(1)).deleteById(REFERENCIA);
    }

}
