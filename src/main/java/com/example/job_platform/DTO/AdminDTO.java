package com.example.job_platform.DTO;

import com.example.job_platform.Enum.Role;

public class AdminDTO {
	
	public Long id;
	public String email;
	public Role role;
	public boolean active;
	
	
	public AdminDTO() {}
	public AdminDTO(Long id,String email,Role role,boolean active) {
		this.id=id;
		this.email=email;
		this.role=role;
		this.active=active;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
