package com.example.job_platform.DTO;

import com.example.job_platform.Enum.ApplicationStatus;

public class ApplicationDTO {
	
	public Long id;
	public Long jobId;
	public String employeeEmail;
	public String recruiterEmail;
	public ApplicationStatus status;
	public String resumeURL;
	
	
	
	public ApplicationDTO(){}
	public ApplicationDTO(Long id,Long jobId,String employeeEmail,ApplicationStatus status,String recruiterEmail,String resumeURL ){
		this.id=id;
		this.jobId=jobId;
		this.employeeEmail=employeeEmail;
		this.recruiterEmail=recruiterEmail;
		this.status=status;
	}
	public String getRecruiterEmail() {
		return recruiterEmail;
	}
	public void setRecruiterEmail(String recruiterEmail) {
		this.recruiterEmail = recruiterEmail;
	}
	public String getResumeURL() {
		return resumeURL;
	}
	public void setResumeURL(String resumeURL) {
		this.resumeURL = resumeURL;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public ApplicationStatus getStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	
	
	

}
