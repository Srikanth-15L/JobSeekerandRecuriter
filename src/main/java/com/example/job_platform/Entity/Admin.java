package com.example.job_platform.Entity;

import javax.persistence.*;

import com.example.job_platform.Enum.Role;

@Entity
@Table(name="admins")
public class Admin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String email;
	private Role role;
	private boolean active=true;
	
	
	
	public Admin() {}
	public Admin(Long id,String email,Role role,boolean active) {
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
