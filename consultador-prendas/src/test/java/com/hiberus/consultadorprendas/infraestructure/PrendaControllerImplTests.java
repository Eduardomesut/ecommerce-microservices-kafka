package com.hiberus.consultadorprendas.infraestructure;

import static com.hiberus.consultadorprendas.TestUtils.getPrenda;
import static com.hiberus.consultadorprendas.TestUtils.getPrendaAsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hiberus.consultadorprendas.domain.model.Prenda;
import com.hiberus.consultadorprendas.domain.repository.PrendaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PrendaControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PrendaRepository prendaRepository;

    @Test
    void deberiaDevolver200AlConsultarUnaPrenda() throws Exception {
        // Given a prenda saved in database
        Prenda prenda = getPrenda("prenda");
        String expectedPrenda = getPrendaAsString("prenda");
        prendaRepository.save(prenda);

        // When requesting it to microservice
        mockMvc.perform(get("/prendas/"+prenda.getReferencia()))

        // Then microservice returns 200
        .andExpect(status().isOk())
            .andExpect(content().string(expectedPrenda));
    }


    @Test
    void deberiaDevolver200AlConsultarUnaListaDePrendas() throws Exception {
        // Given a prenda saved in database
        Prenda prenda = getPrenda("prenda");
        String expectedPrenda = getPrendaAsString("prenda");
        prendaRepository.save(prenda);

        // When requesting it to microservice
        mockMvc.perform(get("/prendas/"))

        // Then microservice returns 200
        .andExpect(status().isOk())
            .andExpect(content().string("["+expectedPrenda+"]"));

    }
}