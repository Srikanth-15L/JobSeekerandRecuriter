package com.example.job_platform.DTO;

public class CreateApplicationDTO {
	
	public Long jobId;
	public String employeeEmail;
	public String recruiterEmail;
	public String resumeURL;
	
	
	public CreateApplicationDTO() {}
	
	public CreateApplicationDTO(Long jobId,String employeeEmail,String recruiterEmail,String resumeURL) {
		this.jobId=jobId;
		this.employeeEmail=employeeEmail;
		this.recruiterEmail=recruiterEmail;
		this.resumeURL=resumeURL;
	}

}
