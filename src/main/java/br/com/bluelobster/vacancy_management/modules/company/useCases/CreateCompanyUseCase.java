package br.com.bluelobster.vacancy_management.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bluelobster.vacancy_management.modules.company.entities.CompanyEntity;
import br.com.bluelobster.vacancy_management.modules.company.repositories.CompanyRepository;
import br.com.bluelobster.vacancy_management.modules.exceptions.UserAlreadyExistsException;

public class CreateCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;
  
  public CompanyEntity excecute(CompanyEntity companyEntity) {
    this.companyRepository
      .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
      .ifPresent(user -> {
        throw new UserAlreadyExistsException();
      });
    return this.companyRepository.save(companyEntity);
  }

}
