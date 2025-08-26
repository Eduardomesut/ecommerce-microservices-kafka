package com.hiberus.enviadorprendas.infraestructure.rest;

import static com.hiberus.enviadorprendas.TestUtils.getPrenda;
import static com.hiberus.enviadorprendas.TestUtils.getPrendaAsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hiberus.enviadorprendas.domain.model.Prenda;
import com.hiberus.enviadorprendas.infraestructure.kafka.service.PrendaServiceImpl;
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
class PrendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PrendaServiceImpl prendaService;

    @Test
    void deberiaDevolver202AlCrearUnaPrenda() throws Exception {
        // Given a prenda
        String prenda = getPrendaAsString("prenda");
        // -- and send to kafka is mocked
        doNothing().when(prendaService).crear(any());

        // When create a prenda
        mockMvc.perform(post("/prendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(prenda))

        //Then microservice returns a 202
        .andExpect(status().isAccepted());
    }


    @Test
    void deberiaDevolver202AlEliminarPrenda() throws Exception {
        // Given a prenda
        Prenda prenda = getPrenda("prenda");
        // -- and send to kafka is mocked
        doNothing().when(prendaService).eliminar(any());

        // When delete a prenda
        mockMvc.perform(delete("/prendas/" + prenda.getReferencia())
                        .contentType(MediaType.APPLICATION_JSON))

        // Then microservice returns a 202
        .andExpect(status().isAccepted());
    }
}
