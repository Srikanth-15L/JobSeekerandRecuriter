package com.example.job_platform.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.job_platform.Entity.Recruiter;

@Repository

public interface RecruiterRepository extends JpaRepository<Recruiter,Long> {
	Optional<Recruiter >findByEmail(String recruiterEmail);

}
