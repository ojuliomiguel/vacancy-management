package br.com.bluelobster.vacancy_management.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.bluelobster.vacancy_management.modules.company.entities.CompanyEntity;
import br.com.bluelobster.vacancy_management.modules.company.repositories.CompanyRepository;
import br.com.bluelobster.vacancy_management.modules.exceptions.UserAlreadyExistsException;

@Service
public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  public CompanyEntity execute(CompanyEntity companyEntity) {
    this.companyRepository
      .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
      .ifPresent(user -> {
        throw new UserAlreadyExistsException();
      });

    var password = passwordEncoder.encode(companyEntity.getPassword());
    companyEntity.setPassword(password);
    
    return this.companyRepository.save(companyEntity);
  }

}
