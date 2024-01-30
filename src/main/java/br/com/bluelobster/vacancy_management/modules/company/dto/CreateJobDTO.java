package br.com.bluelobster.vacancy_management.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {
  @Schema(example = "Software TI")
  private String description;

  @Schema(example = "Guns insurance, 401k, remote work options")
  private String benefits;

  @Schema(example = "Senior")
  private String level;
}
