package com.example.job_platform.Entity;

import javax.persistence.*;

import com.example.job_platform.Enum.ApplicationStatus;



@Entity
@Table(name="applications")
public class Application {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long jobId;
	private String employeeEmail;
	private String recruiterEmail;
	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;
	private String resumeURL;
	
	
	public Application() {}
	public Application(Long id,Long jobId,String employeeEmail,ApplicationStatus status,String recruiterEmail,String resumeURL) {
		this.id=id;
		this.jobId=jobId;
		this.employeeEmail=employeeEmail;
		this.recruiterEmail=recruiterEmail;
		this.status=status;
		this.resumeURL=resumeURL;
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
	public ApplicationStatus getStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	
	
	
	
	

}
