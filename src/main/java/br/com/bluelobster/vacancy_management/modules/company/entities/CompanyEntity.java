package br.com.bluelobster.vacancy_management.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank
  @Pattern(regexp = "\\S+", message = "The [username] field must not contain spaces")
  private String username;

  @Email(message = "The [email] field must contain a valid email address")
  private String email;

  @Length(min = 18, max = 100, message = "The password must contain between (18) and (100) characters")
  private String password;

  private String website;
  private String name;
  private String description;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
