package com.titulizacion.poc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarteraControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listarCarterasDevuelveLosDatosDeEjemplo() throws Exception {
        mockMvc.perform(get("/api/carteras"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", org.hamcrest.Matchers.not(org.hamcrest.Matchers.empty())));
    }

    @Test
    void crearCarteraConDatosValidosDevuelve201() throws Exception {
        String payload = """
                {
                  "nombre": "Cartera de prueba IT",
                  "fechaConstitucion": "2026-05-01",
                  "moneda": "EUR",
                  "estado": "ACTIVA",
                  "valorNominalTotal": 1000000
                }
                """;

        mockMvc.perform(post("/api/carteras")
                        .contentType("application/json")
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Cartera de prueba IT"));
    }

    @Test
    void crearCarteraConDatosInvalidosDevuelve400() throws Exception {
        String payload = """
                {
                  "nombre": "ab",
                  "moneda": "EUR"
                }
                """;

        mockMvc.perform(post("/api/carteras")
                        .contentType("application/json")
                        .content(payload))
                .andExpect(status().isBadRequest());
    }

    @Test
    void obtenerCarteraInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/api/carteras/999999"))
                .andExpect(status().isNotFound());
    }
}
