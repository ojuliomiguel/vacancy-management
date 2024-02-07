package br.com.bluelobster.vacancy_management.modules.company.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bluelobster.vacancy_management.modules.company.dto.CreateJobDTO;
import br.com.bluelobster.vacancy_management.modules.company.entities.CompanyEntity;
import br.com.bluelobster.vacancy_management.modules.company.helpers.CompanyJTWToken;
import br.com.bluelobster.vacancy_management.modules.company.repositories.CompanyRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateJobControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private WebApplicationContext context;

    private CompanyEntity companyEntityDB;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
    }


    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {

        var company = CompanyEntity
            .builder()
            .description("COMPANY_DESCRIPTION")
            .email("mail@companyxpto.com")
            .password("12345678900111213777777777777")
            .username("XPTO_COMPANY")
            .name("XPTO")
            .build();

        companyEntityDB = this.companyRepository.save(company);

        var token = CompanyJTWToken.generate(companyEntityDB.getId().toString());
        

        var createdJobDTO = CreateJobDTO.builder()
            .benefits("BENEFITS_TEST")
            .level("LEVEL_TEST")
            .description("DESCRIPTION_TEST")
            .build();

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/company/job")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", token)
            .content(objectToJson(createdJobDTO)))
            .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);

        //this.companyRepository.delete(companyEntityDB);
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
