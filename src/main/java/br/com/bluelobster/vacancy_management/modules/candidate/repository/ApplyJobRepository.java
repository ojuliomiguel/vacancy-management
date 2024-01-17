package br.com.bluelobster.vacancy_management.modules.candidate.repository;

import br.com.bluelobster.vacancy_management.modules.candidate.entity.ApplyJobEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
    
}
