package com.example.job_platform.DTO;

public class RecruiterDTO {
	
	public Long id;
	public String recruiterName;
	public String companyName;
	public String recruiterEmail;
	public String recruiterPhone;
	public String designation;
	
	
	
	
	
	public RecruiterDTO() {}
	public RecruiterDTO(Long id , String recruiterName,String companyName,String recruiterEmail,String  recruiterPhone,String designation ) {
		
		this.id=id;
		this.recruiterName=recruiterName;
		this.companyName=companyName;
		this.recruiterEmail=recruiterEmail;
		this.recruiterPhone=recruiterPhone;
		this.designation=designation;
	}

}
