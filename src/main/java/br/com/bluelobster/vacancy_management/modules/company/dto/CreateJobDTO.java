package br.com.bluelobster.vacancy_management.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {
  private String description;
  private String benefits;
  private String level;
}
