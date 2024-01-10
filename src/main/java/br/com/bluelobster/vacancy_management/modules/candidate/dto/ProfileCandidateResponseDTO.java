package br.com.bluelobster.vacancy_management.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

  @Schema(example = "Delphi Software Developer")
  private String description;

  @Schema(example = "Bill_blue")
  private String username;

  @Schema(example = "billblue@example.com")
  private String email;

  private UUID id;

  @Schema(example = "Bill")
  private String name;
}
