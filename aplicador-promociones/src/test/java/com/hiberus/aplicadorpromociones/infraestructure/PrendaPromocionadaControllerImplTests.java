package com.hiberus.aplicadorpromociones.infraestructure;

import static com.hiberus.aplicadorpromociones.TestUtils.NOMBRE_PROMOCION;
import static com.hiberus.aplicadorpromociones.TestUtils.REFERENCIA;
import static com.hiberus.aplicadorpromociones.TestUtils.getPrenda;
import static com.hiberus.aplicadorpromociones.TestUtils.getPrendaPromcionadaPkey;
import static com.hiberus.aplicadorpromociones.TestUtils.getPrendaPromocionada;
import static com.hiberus.aplicadorpromociones.TestUtils.getPromocion;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hiberus.aplicadorpromociones.domain.model.Prenda;
import com.hiberus.aplicadorpromociones.domain.model.Promocion;
import com.hiberus.aplicadorpromociones.domain.repository.PrendaPromocionadaRepository;
import com.hiberus.aplicadorpromociones.domain.repository.PrendaRepository;
import com.hiberus.aplicadorpromociones.domain.repository.PromocionRepository;
import com.hiberus.aplicadorpromociones.infraestructure.kafka.service.PrendaServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PrendaPromocionadaControllerImplTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PrendaRepository prendaRepository;

    @Autowired
    PromocionRepository promocionRepository;

    @Autowired
    PrendaPromocionadaRepository prendaPromocionadaRepository;

    @MockBean
    PrendaServiceImpl prendaService;


    @Test
    void testDeberiaDevolver200AlAplicarUnaPromocionAUnaPrenda() throws Exception {
        // GIVEN una prenda y una promocion guardadas en base de datos
        prendaRepository.save(getPrenda(10.0, 10.0));
        promocionRepository.save(getPromocion(2.0));
        // -- and mockeamos el envio a kafka
        doNothing().when(prendaService).crear(any(Prenda.class));

        // WHEN aplicamos la promocion a la prenda
        mockMvc.perform(
            put("/promociones/aplicar?promocion=" + NOMBRE_PROMOCION + "&prenda=" + REFERENCIA)
                .contentType(MediaType.APPLICATION_JSON)
            )

        // THEN el microservicio responde con status 200
        .andExpect(status().isOk());
        // -- and el nuevo precio promocionado de la prenda es correcto
        Prenda prendaPromocionada = prendaRepository.findById(REFERENCIA).get();
        assertEquals(8.0, prendaPromocionada.getPrecio_promocionado());
    }

    @Test
    void deberiaDevolver200AlDesaplicarUnaPromocionAUnaPrenda() throws Exception {
        // GIVEN una prenda y una promocion guardadas en base de datos
        Prenda prenda = getPrenda(10.0, 8.0);
        Promocion promocion = getPromocion(2.0);
        prendaRepository.save(prenda);
        promocionRepository.save(promocion);
        // -- la promociona aplica actualmente a la prenda
        prendaPromocionadaRepository.save(getPrendaPromocionada(getPrendaPromcionadaPkey(), prenda, promocion));
        // -- and mockeamos el envio a kafka
        doNothing().when(prendaService).eliminar(REFERENCIA);

        // WHEN desaplicamos la promocion a la prenda
        mockMvc.perform(put("/promociones/desaplicar?promocion=" + NOMBRE_PROMOCION + "&prenda=" + REFERENCIA)
                .contentType(MediaType.APPLICATION_JSON))

                // THEN el microservicio responde con status 200
                .andExpect(status().isOk());
        // -- and el nuevo precio promocionado de la prenda es correcto
        Prenda prendaPromocionada = prendaRepository.findById(REFERENCIA).get();
        assertEquals(10.0, prendaPromocionada.getPrecio_promocionado());
    }
}
