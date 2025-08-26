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
class DesaplicarPromocionTest {

    @Mock
    PrendaRepository prendaRepository;

    @Mock
    PromocionRepository promocionRepository;

    @Mock
    PrendaPromocionadaRepository prendaPromocionadaRepository;

    @Mock
    PrendaService prendaService;

    @Test
    void deberiaDesaplicarUnaPromocion() throws PromocionNoExiste, PrendaNoExiste {
        // GIVEN una prenda y una promocion que aplica a la prenda
        when(prendaRepository.findById(REFERENCIA)).thenReturn(Optional.of(getPrenda(10.00, 10.00)));
        when(prendaPromocionadaRepository.existsById(any())).thenReturn(true);
        when(promocionRepository.findById(NOMBRE_PROMOCION)).thenReturn(Optional.of(getPromocion(2.00)));

        // WHEN desaplicamos la promociona a la prenda
        DesaplicarPromocion.desaplicar(getPrendaPromcionadaPkey(), prendaRepository, promocionRepository,
            prendaPromocionadaRepository, prendaService);

        // THEN se busca la prenda
        verify(prendaRepository, times(1)).findById(REFERENCIA);
        // -- and se busca la promocion
        verify(promocionRepository, times(1)).findById(NOMBRE_PROMOCION);
        // -- and se comprueba si la promocion ya aplica a la prenda
        verify(prendaPromocionadaRepository, times(1)).existsById(any(PrendaPromocionadaPkey.class));
        // -- and la promociona ya no aplica a la prenda
        verify(prendaPromocionadaRepository, times(1)).delete(any(PrendaPromocionada.class));
        // -- and se actualiza la prenda con el nuevo precio promocionado
        verify(prendaRepository, times(1)).save(any(Prenda.class));
        // -- an se envia la prenda a un topic de kafka
        verify(prendaService, times(1)).crear(any(Prenda.class));
    }


    @Test
    void deberiaLanzarExcepcionPrendaNoExiste() {
        // GIVEN una promocion
        when(promocionRepository.findById(NOMBRE_PROMOCION)).thenReturn(Optional.of(getPromocion(2.00)));

        // WHEN desaplicamos la promocion a una prenda que no existe, THEN lanza excepcion PrendaNoExiste
        assertThrows(PrendaNoExiste.class, () ->
            DesaplicarPromocion.desaplicar(getPrendaPromcionadaPkey(),
                prendaRepository, promocionRepository, prendaPromocionadaRepository, prendaService)
        );
    }


    @Test
    void deberiaLanzarExcepcionPromocionNoExiste() {
        // GIVEN una prenda
        when(prendaRepository.findById(REFERENCIA)).thenReturn(Optional.of(getPrenda(10.0, 10.0)));

        // WHEN desaplicamos una promocion que no existe a una prenda, THEN lanza excepcion PromocionNoExiste
        assertThrows(PromocionNoExiste.class, () ->
                DesaplicarPromocion.desaplicar(getPrendaPromcionadaPkey(),
                    prendaRepository, promocionRepository, prendaPromocionadaRepository, prendaService)
        );
    }

}
