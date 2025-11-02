package com.example.job_platform.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.job_platform.Entity.JobPost;
import com.example.job_platform.Enum.JobType;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {
	List<JobPost>findByCopanyName(String companyName);
	List<JobPost>findByJobTitle(String jobTitle);
	List<JobPost>findByJobType(JobType jobType);
	List<JobPost>findByJobLocation(String jobLocation);
	List<JobPost>findByPostedByRecruiter(String recruiterEmail);

}
