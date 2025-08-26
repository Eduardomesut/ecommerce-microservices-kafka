package com.hiberus.aplicadorpromociones.application;

import static com.hiberus.aplicadorpromociones.TestUtils.REFERENCIA;
import static com.hiberus.aplicadorpromociones.TestUtils.getPrenda;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hiberus.aplicadorpromociones.domain.model.Prenda;
import com.hiberus.aplicadorpromociones.domain.repository.PrendaRepository;
import com.hiberus.aplicadorpromociones.domain.service.PrendaService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CrearPrendaTest {

    @Mock
    PrendaRepository prendaRepository;

    @Mock
    PrendaService prendaService;

    @Test
    void crearPrenda() {
        // Given un mock que devuelve una prenda
        Prenda prenda = getPrenda(10.0,10.0);
        when(prendaRepository.findById(REFERENCIA)).thenReturn(Optional.of(prenda));

        // WHEN se crea la prenda
        CrearPrenda.crear(prenda, prendaRepository, prendaService);

        // THEN la prenda es creada
        verify(prendaRepository, times(1)).save(prenda);
    }

}
