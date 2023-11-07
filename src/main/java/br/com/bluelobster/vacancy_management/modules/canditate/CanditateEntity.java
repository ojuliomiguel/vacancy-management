package br.com.bluelobster.vacancy_management.modules.canditate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CanditateEntity {
  private UUID id;
  private String name;

  @Pattern(regexp = "^[^\\s]+$", message = "The [username] field must not contain spaces.")
  private String username;

  @Email(message = "The [email] field must be a valid email")
  private String email;

  @Length(min = 18, max = 256, message = "The password must have at least 18 characters.")
  private String password;
  private String description;
  private String curriculum;
}
