package com.example.job_platform.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.job_platform.DTO.JobPostDTO;
import com.example.job_platform.Entity.JobPost;
import com.example.job_platform.Enum.JobType;
import com.example.job_platform.Repository.JobPostRepository;

@Service
public class JobPostService {

	
	@Autowired
	private JobPostRepository jobPostRepository;
	
	
	public JobPost createJob(JobPostDTO dto) {
		JobPost job = new JobPost();
		
		job.setId(dto.id);
		job.setCompanyName(dto.companyName);
		job.setJobDescription(dto.jobDescription);
		job.setJobLocation(dto.jobLocation);
		job.setJobTitle(dto.jobTitle);
		job.setJobType(dto.jobType);
		job.setPostedDate(dto.postedDate);
		job.setDeadLineDate(dto.deadLineDate);
		job.setRecruiterEmail(dto.recruiterEmail);
		
		return jobPostRepository.save(job);
		}
	
	public List<JobPost>getAllJobs(){
		return jobPostRepository.findAll();
	}
	
	public List<JobPost>getJobByRecruiter(String recruiterEmail){
		return jobPostRepository.findByPostedByRecruiter(recruiterEmail);
	}
	
	public List<JobPost>searchJobByCompanyName(String companyName){
		return jobPostRepository.findByCopanyName(companyName);
	}
	public List<JobPost>searchJobByJobTitle(String jobTitle){
		return jobPostRepository.findByJobTitle(jobTitle);
	}
	
	public List<JobPost>searchJobByJobType(JobType jobType){
		return jobPostRepository.findByJobType(jobType);
	}
	public List<JobPost>searchJobByJobLocation(String jobLocation){
		return jobPostRepository.findByJobLocation(jobLocation);
	}
}