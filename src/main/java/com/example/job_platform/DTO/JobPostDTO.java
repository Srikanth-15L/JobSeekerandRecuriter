package com.example.job_platform.DTO;

import java.time.LocalDate;

import com.example.job_platform.Enum.JobType;

public class JobPostDTO {
	
	public Long id;
	public String recruiterEmail;
	public String companyName;
	public String jobTitle;
	public JobType jobType;
	public String jobDescription;
	public String jobLocation;
	public LocalDate postedDate;
	public LocalDate deadLineDate;
	
	
	
	public JobPostDTO() {}
	
	public JobPostDTO(Long id,String recruiterEmail,String companyName,String jobTitle,JobType jobType,String jobDescription,String jobLocation,LocalDate postedDate,LocalDate deadLineDate) {
		
		this.id=id;
		this.recruiterEmail=recruiterEmail;
		this.companyName=companyName;
		this.jobDescription=jobDescription;
		this.jobTitle=jobTitle;
		this.jobType=jobType;
		this.jobLocation=jobLocation;
		this.postedDate=postedDate;
		this.deadLineDate=deadLineDate;
	}
	

}
