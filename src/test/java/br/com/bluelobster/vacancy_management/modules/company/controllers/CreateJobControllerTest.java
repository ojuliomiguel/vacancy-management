package br.com.bluelobster.vacancy_management.modules.company.controllers;

import java.util.List;

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
import br.com.bluelobster.vacancy_management.modules.company.entities.JobEntity;
import br.com.bluelobster.vacancy_management.modules.company.helpers.CompanyJTWToken;
import br.com.bluelobster.vacancy_management.modules.company.repositories.CompanyRepository;
import br.com.bluelobster.vacancy_management.modules.company.repositories.JobRepository;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateJobControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

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
            .description("We are looking for a Python Developer")
            .email("mail@companyxpto.com")
            .password("3beac352-3c72-4887-8593-722db355af96")
            .username("companyXPTO")
            .name("Company XPTO")
            .build();

        companyEntityDB = this.companyRepository.save(company);

        var token = CompanyJTWToken.generate(companyEntityDB.getId().toString());

        var createdJobDTO = CreateJobDTO.builder()
            .benefits("Gym card")
            .level("junior")
            .description("Python Developer")
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/company/job")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", token)
            .content(objectToJson(createdJobDTO)))
            .andExpect(MockMvcResultMatchers.status().isOk());


        List<JobEntity> jobs = this.jobRepository.findByCompanyEntity(companyEntityDB);
        this.jobRepository.deleteAll(jobs);
        this.companyRepository.delete(companyEntityDB);
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
