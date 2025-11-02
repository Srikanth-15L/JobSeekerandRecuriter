package com.example.job_platform.DTO;

public class EmployeeDTO {
	
	public Long id;
	public String name;
	public String email;
	public String phone;
	public String qualification;
	public String resumeURL;
	
	public EmployeeDTO() {}
	public EmployeeDTO(Long id,String name,String email,String phone,String qualification,String resumeURL ) {
		this.id=id;
		this.name=name;
		this.email=email;
		this.phone=phone;
		this.qualification=qualification;
		this.resumeURL=resumeURL;
	}
	

}
