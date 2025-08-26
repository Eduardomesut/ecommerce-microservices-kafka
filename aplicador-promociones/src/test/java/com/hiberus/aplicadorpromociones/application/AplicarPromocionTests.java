package com.hiberus.aplicadorpromociones.application;

import static com.hiberus.aplicadorpromociones.TestUtils.NOMBRE_PROMOCION;
import static com.hiberus.aplicadorpromociones.TestUtils.REFERENCIA;
import static com.hiberus.aplicadorpromociones.TestUtils.getPrenda;
import static com.hiberus.aplicadorpromociones.TestUtils.getPrendaPromcionadaPkey;
import static com.hiberus.aplicadorpromociones.TestUtils.getPromocion;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hiberus.aplicadorpromociones.domain.exceptions.PrendaNoExiste;
import com.hiberus.aplicadorpromociones.domain.exceptions.PromocionNoExiste;
import com.hiberus.aplicadorpromociones.domain.model.Prenda;
import com.hiberus.aplicadorpromociones.domain.model.PrendaPromocionada;
import com.hiberus.aplicadorpromociones.domain.model.PrendaPromocionadaPkey;
import com.hiberus.aplicadorpromociones.domain.repository.PrendaPromocionadaRepository;
import com.hiberus.aplicadorpromociones.domain.repository.PrendaRepository;
import com.hiberus.aplicadorpromociones.domain.repository.PromocionRepository;
import com.hiberus.aplicadorpromociones.domain.service.PrendaService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class AplicarPromocionTests {

    @Mock
    PrendaRepository prendaRepository;

    @Mock
    PromocionRepository promocionRepository;

    @Mock
    PrendaPromocionadaRepository prendaPromocionadaRepository;

    @Mock
    PrendaService prendaService;

    @Test
    void deberiaAplicarUnaPromocion() throws PromocionNoExiste, PrendaNoExiste {
        // GIVEN una prenda y una promocion
        when(prendaRepository.findById(REFERENCIA)).thenReturn(Optional.of(getPrenda(10.00,10.00)));
        when(promocionRepository.findById(NOMBRE_PROMOCION)).thenReturn(Optional.of(getPromocion(2.00)));

        // WHEN aplicamos la promocion a la prenda
        AplicarPromocion.aplicar(getPrendaPromcionadaPkey(),
            prendaRepository, promocionRepository, prendaPromocionadaRepository, prendaService);

        // THEN la prenda se busca en el repositorio
        verify(prendaRepository, times(1)).findById(REFERENCIA);
        // -- and la promocion se busca en el repositorio
        verify(promocionRepository, times(1)).findById(NOMBRE_PROMOCION);
        // -- and se comprueba si existe la relacion N a M entre la prenda y la promocion
        verify(prendaPromocionadaRepository, times(1)).existsById(any(PrendaPromocionadaPkey.class));
        // -- and se crea la nueva relacion N a M
        verify(prendaPromocionadaRepository, times(1)).save(any(PrendaPromocionada.class));
        // -- and se actualiza el precio de la prenda
        verify(prendaRepository, times(1)).save(any(Prenda.class));
        // -- and se envia la prenda al topic de kafka
        verify(prendaService, times(1)).crear(any(Prenda.class));
    }

    @Test
    void deberiaLanzarExcepcionPrendaNoExiste() {
        // GIVEN una promocion
        when(promocionRepository.findById(NOMBRE_PROMOCION)).thenReturn(Optional.of(getPromocion(2.00)));

        // WHEN aplicamos una promocion a una prenda que no existe, THEN lanza excepcion PrendaNoExiste
        assertThrows(PrendaNoExiste.class, () ->
            AplicarPromocion.aplicar(getPrendaPromcionadaPkey(),
                prendaRepository, promocionRepository, prendaPromocionadaRepository, prendaService)
        );
    }

    @Test
    void deberiaLanzarExcepcionPromocionNoExiste() {
        // GIVEN una prenda
        when(prendaRepository.findById(REFERENCIA)).thenReturn(Optional.of(getPrenda(10.00, 10.00)));

        // WHEN aplicamos una promocion que no existe a una prenda, THEN lanza excepcion PromocionNoExiste
        assertThrows(PromocionNoExiste.class, () ->
                AplicarPromocion.aplicar(getPrendaPromcionadaPkey(),
                    prendaRepository, promocionRepository, prendaPromocionadaRepository, prendaService)
        );
    }

}
