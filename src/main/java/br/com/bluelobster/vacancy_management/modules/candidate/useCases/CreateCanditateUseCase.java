package br.com.bluelobster.vacancy_management.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.bluelobster.vacancy_management.exceptions.UserAlreadyExistsException;
import br.com.bluelobster.vacancy_management.modules.candidate.CandidateEntity;
import br.com.bluelobster.vacancy_management.modules.candidate.CandidateRepository;

@Service
public class CreateCanditateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository
        .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent(user -> {
          throw new UserAlreadyExistsException();
        });
    
    var password = this.passwordEncoder.encode(candidateEntity.getPassword()); 
    candidateEntity.setPassword(password);   
    
    return this.candidateRepository.save(candidateEntity);
  }
}
