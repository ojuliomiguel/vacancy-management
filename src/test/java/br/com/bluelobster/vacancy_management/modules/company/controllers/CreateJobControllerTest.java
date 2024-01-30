package br.com.bluelobster.vacancy_management.modules.company.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bluelobster.vacancy_management.modules.company.dto.CreateJobDTO;

public class CreateJobControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {
        var createdJobDTO = CreateJobDTO.builder()
            .benefits("BENEFITS_TEST")
            .level("LEVEL_TEST")
            .description("DESCRIPTION_TEST")
            .build();

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/company/job")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectToJson(createdJobDTO)))
            .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }

    private static String objectToJson(Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
