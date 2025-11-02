package com.example.job_platform.Entity;

import javax.persistence.*;


@Entity
@Table(name="plans")
public class SubscriptionPlan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	private int maxJobs;
	private int maxProfiles;
	private int validityDays;
	private double prices;
	
	
	public SubscriptionPlan() {}
	public SubscriptionPlan(Long id,String name,int maxJobs,int maxProfiles,int validityDays,double prices) {
		
		this.id=id;
		this.name=name;
		this.maxJobs=maxJobs;
		this.maxProfiles=maxProfiles;
		this.prices=prices;
		this.validityDays=validityDays;
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMaxJobs() {
		return maxJobs;
	}
	public void setMaxJobs(int maxJobs) {
		this.maxJobs = maxJobs;
	}
	public int getMaxProfiles() {
		return maxProfiles;
	}
	public void setMaxProfiles(int maxProfiles) {
		this.maxProfiles = maxProfiles;
	}
	public int getValidityDays() {
		return validityDays;
	}
	public void setValidityDays(int validityDays) {
		this.validityDays = validityDays;
	}
	public double getPrices() {
		return prices;
	}
	public void setPrices(double prices) {
		this.prices = prices;
	}
	
	
	

}
