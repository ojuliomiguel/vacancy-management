package br.com.bluelobster.vacancy_management.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluelobster.vacancy_management.modules.candidate.CandidateEntity;
import br.com.bluelobster.vacancy_management.modules.candidate.CandidateRepository;
import br.com.bluelobster.vacancy_management.modules.exceptions.UserAlreadyExistsException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/canditate")
public class CanditadeController {

  @Autowired
  private CandidateRepository candidateRepository;

  @PostMapping("/")
  public ResponseEntity<CandidateEntity> create(@Valid @RequestBody CandidateEntity candidateEntity) {

    this.candidateRepository
      .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
      .ifPresent(user -> { throw new UserAlreadyExistsException(); });

    this.candidateRepository.save(candidateEntity);

    return new ResponseEntity<>(candidateEntity, HttpStatus.CREATED);
  }
}
