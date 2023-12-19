package br.com.bluelobster.vacancy_management.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluelobster.vacancy_management.modules.candidate.CandidateEntity;
import br.com.bluelobster.vacancy_management.modules.candidate.useCases.CreateCanditateUseCase;
import br.com.bluelobster.vacancy_management.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreateCanditateUseCase createCanditateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @PostMapping("")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var candidate = this.createCanditateUseCase.execute(candidateEntity);
      return ResponseEntity.created(null).body(candidate);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }

  @GetMapping("")
  @PreAuthorize("hasRole('CANDIDATE')")
  public ResponseEntity<Object> get(HttpServletRequest request) {
    var idCandidate = request.getAttribute("candidate_id");
    try {
      var profile = this.profileCandidateUseCase
        .execute(UUID.fromString(idCandidate.toString()));
      return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
