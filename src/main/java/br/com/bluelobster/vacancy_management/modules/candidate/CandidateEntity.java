package br.com.bluelobster.vacancy_management.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(example = "Arnold David")
  private String name;

  @Pattern(regexp = "^[^\\s]+$", message = "The [username] field must not contain spaces.")
  @Schema(example = "arnold_david", requiredMode = RequiredMode.REQUIRED)
  private String username;

  @Email(message = "The [email] field must be a valid email")
  @Schema(example = "arnold_david@example.com", requiredMode = RequiredMode.REQUIRED)
  private String email;

  @Length(min = 18, max = 256, message = "The password must have at least 18 characters.")
  @Schema(example = "12345678", requiredMode = RequiredMode.REQUIRED, minLength = 18, maxLength = 256)
  private String password;

  @Schema(example = "Software Developer", requiredMode = RequiredMode.REQUIRED)
  private String description;

  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
