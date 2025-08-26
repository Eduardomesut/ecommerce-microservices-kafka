package com.hiberus.enviadorpromociones.infraestructure.rest;

import static com.hiberus.enviadorpromociones.TestUtils.getPromocion;
import static com.hiberus.enviadorpromociones.TestUtils.getPromocionAsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hiberus.enviadorpromociones.domain.Promocion;
import com.hiberus.enviadorpromociones.infraestructure.kafka.service.service.PromocionServiceImpl;
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
class PromocionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PromocionServiceImpl promocionService;

    @Test
    void deberiaDevolver202AlCrearUnaPromocion() throws Exception {
        // Given a promotion
        String promocion = getPromocionAsString("promocion");
        // -- and send to kafka is mocked
        doNothing().when(promocionService).crear(any());

        // When is created
        mockMvc.perform(post("/promociones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(promocion))

        // Then microservice returns 202
        .andExpect(status().isAccepted());
    }


    @Test
    void deberiaDevolver202AlEliminarPrenda() throws Exception {
        // Given a promotion
        Promocion promocion = getPromocion("promocion");
        // -- and send to kafka is mocked
        doNothing().when(promocionService).eliminar(any());

        // When is deleted
        mockMvc.perform(delete("/promociones/" + promocion.getNombre())
                        .contentType(MediaType.APPLICATION_JSON))

        // Then microservice returns 202
        .andExpect(status().isAccepted());
    }
}
