package br.com.bluelobster.vacancy_management.modules.candidate.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluelobster.vacancy_management.modules.candidate.CandidateEntity;
import br.com.bluelobster.vacancy_management.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.bluelobster.vacancy_management.modules.candidate.useCases.CreateCanditateUseCase;
import br.com.bluelobster.vacancy_management.modules.candidate.useCases.ListJobsByFilterUseCase;
import br.com.bluelobster.vacancy_management.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.bluelobster.vacancy_management.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate", description = "Candidates's informations")
public class CandidateController {

  @Autowired
  private CreateCanditateUseCase createCanditateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @Autowired
  private ListJobsByFilterUseCase listJobsByFilterUseCase;

  @Autowired
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @PostMapping("")
  @Operation(summary = "Create Candidate ", description = "This function register a new candidate")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
      }),
      @ApiResponse(responseCode = "400", description = "User already exists")
  })
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
  @Operation(summary = "Candidate Profile", description = "This function return the candidate's profile")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
      }),
      @ApiResponse(responseCode = "400", description = "User not found")
  })
  @SecurityRequirement(name = "jwt_auth")
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

  @GetMapping("/jobs")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(summary = "List jobs by filter", description = "This function return a list of available jobs by filter to an candidate")
  @SecurityRequirement(name = "jwt_auth")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
      }),
  })
  public List<JobEntity> findJobsByFilter(@RequestParam String filter) {
    return this.listJobsByFilterUseCase.execute(filter);
  }

  @PostMapping("job/apply")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(summary = "Apply for a job", description = "This function apply for a job")
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID idJob) {
    var idCandidate = request.getAttribute("candidate_id");
    var idCandidateUUID = UUID.fromString(idCandidate.toString());

    try {
      var result = this.applyJobCandidateUseCase.execute(idCandidateUUID, idJob);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
