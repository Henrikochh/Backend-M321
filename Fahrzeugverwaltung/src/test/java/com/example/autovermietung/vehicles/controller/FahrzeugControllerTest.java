package com.example.autovermietung.vehicles.controller;

import com.example.autovermietung.vehicles.service.FahrzeugService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(FahrzeugController.class)
public class FahrzeugControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FahrzeugService fahrzeugService;

    @Test
    public void testGetAllFahrzeuge() throws Exception {
        when(fahrzeugService.getAllFahrzeuge()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/fahrzeuge"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
