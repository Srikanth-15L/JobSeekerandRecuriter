package com.example.job_platform.DTO;

public class SystemStatusDTO {
	
	public int totalUsers;
	public int totlRecruiters;
	public int totalEmployees;
	public int totalJobPosts;
	public int totalApplicatiions;
	
	
	
	public SystemStatusDTO() {}
	public SystemStatusDTO(int totalUsers,int totlRecruiters,int totalEmployees,int totalApplicatiions,int totalJobPosts) {
		
		this.totalUsers=totalUsers;
		this.totalEmployees=totalEmployees;
		this.totlRecruiters=totlRecruiters;
		this.totalJobPosts=totalJobPosts;
		this.totalApplicatiions=totalApplicatiions;
		
	}
	public int getTotalUsers() {
		return totalUsers;
	}
	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}
	public int getTotlRecruiters() {
		return totlRecruiters;
	}
	public void setTotlRecruiters(int totlRecruiters) {
		this.totlRecruiters = totlRecruiters;
	}
	public int getTotalEmployees() {
		return totalEmployees;
	}
	public void setTotalEmployees(int totalEmployees) {
		this.totalEmployees = totalEmployees;
	}
	public int getTotalJobPosts() {
		return totalJobPosts;
	}
	public void setTotalJobPosts(int totalJobPosts) {
		this.totalJobPosts = totalJobPosts;
	}
	public int getTotalApplicatiions() {
		return totalApplicatiions;
	}
	public void setTotalApplicatiions(int totalApplicatiions) {
		this.totalApplicatiions = totalApplicatiions;
	}
	
	
	

}
