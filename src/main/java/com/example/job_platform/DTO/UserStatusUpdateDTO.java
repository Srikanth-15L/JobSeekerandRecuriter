package com.example.job_platform.DTO;

public class UserStatusUpdateDTO {
	
	public Long userId;
	public boolean active;
	
	
	public UserStatusUpdateDTO() {}
	public UserStatusUpdateDTO(Long userId,boolean active) {
		this.userId=userId;
		this.active=active;
		
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	

}
