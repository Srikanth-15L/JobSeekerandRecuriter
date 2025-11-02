package com.example.job_platform.DTO;

public class SubscriptionPlanDTO {

	public Long id;
	public String name;
	
	public int maxJobs;
	public int maxProfiles;
	public int validityDays;
	public double prices;
	
	
	public SubscriptionPlanDTO() {}
	public SubscriptionPlanDTO(Long id,String name,int maxJobs,int maxProfiles,int validityDays,double prices) {
		
		this.id=id;
		this.name=name;
		this.maxJobs=maxJobs;
		this.maxProfiles=maxProfiles;
		this.prices=prices;
		this.validityDays=validityDays;
		
	}
}
