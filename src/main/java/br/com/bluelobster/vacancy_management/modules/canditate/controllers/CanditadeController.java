package br.com.bluelobster.vacancy_management.modules.canditate.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluelobster.vacancy_management.modules.canditate.CanditateEntity;

@RestController
@RequestMapping("/canditate")
public class CanditadeController {
  
  @PostMapping("/")
  public void create(@RequestBody CanditateEntity canditateEntity) {
    System.out.println(canditateEntity);
  }
}
