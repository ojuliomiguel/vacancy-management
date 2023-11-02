package br.com.bluelobster.vacancy_management.modules.canditate;

import java.util.UUID;

import lombok.Data;

@Data
public class CanditateEntity {
  private UUID id;
  private String name;
  private String username;
  private String email;
  private String password;
  private String description;
  private String curriculum;
}
