package br.com.bluelobster.vacancy_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Vacancy Management",
		description = "Api designed to streamline the process of managing job vacancies.",
		version = "1"
	)
)
public class VacancyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacancyManagementApplication.class, args);
	}

}
