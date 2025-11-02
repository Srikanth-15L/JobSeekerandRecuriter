package com.example.job_platform.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.job_platform.DTO.RecruiterDTO;
import com.example.job_platform.Entity.Recruiter;
import com.example.job_platform.Repository.RecruiterRepository;

@Service
public class RecruiterService {

	@Autowired
	private RecruiterRepository recruiterRepository;
	
	
	public Recruiter createOrUpdateRecruiter(RecruiterDTO dto) {
		Optional<Recruiter> existing = recruiterRepository.findByEmail(dto.recruiterEmail);
		
		Recruiter recruiter = existing.orElse(new Recruiter());
		
		recruiter.setId(dto.id);
		recruiter.setRecruiterName(dto.recruiterName);
		recruiter.setCompanyName(dto.companyName);
		recruiter.setRecruiterEmail(dto.recruiterEmail);
		recruiter.setRecruiterPhone(dto.recruiterPhone);
		recruiter.setDesignation(dto.designation);
		
		return recruiterRepository.save(recruiter);
		
	}
	
	public Recruiter getRecruiterByEmail(String recruiterEmail ) {
		return recruiterRepository.findByEmail(recruiterEmail).orElseThrow(()-> new RuntimeException("Recruiter not found"));
	}
}
