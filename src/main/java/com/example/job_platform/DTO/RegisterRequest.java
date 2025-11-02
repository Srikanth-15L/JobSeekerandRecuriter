package com.example.job_platform.DTO;

import com.example.job_platform.Enum.Role;

public class RegisterRequest {
	
	public Long id;
	public String name;
	public String email;
	public String password;
	public Role role;
	

	public RegisterRequest(Long id,String name,String password,String email,Role role) {
		this.id=id;
		this.name=name;
		this.email= email;
		this.password=password;
		this.role=role;
	}
}
