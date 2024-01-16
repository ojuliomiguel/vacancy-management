package br.com.bluelobster.vacancy_management.modules.candidate.useCases;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.bluelobster.vacancy_management.exceptions.UserNotFoundException;
import br.com.bluelobster.vacancy_management.modules.candidate.CandidateRepository;
import br.com.bluelobster.vacancy_management.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

	@InjectMocks
	private ApplyJobCandidateUseCase applyJobCandidateUseCase;

	@Mock
	private CandidateRepository candidateRepository;

	@Mock
	private JobRepository jobRepository;

	@Test
	public void should_not_be_able_to_apply_job_with_candidate_not_found() {
		try {
			applyJobCandidateUseCase.execute(null, null);
		} catch (Exception e) {
			assertThat(e).isInstanceOf(UserNotFoundException.class);
		}
	}

}
