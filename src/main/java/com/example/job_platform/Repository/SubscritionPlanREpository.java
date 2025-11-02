package com.example.job_platform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.job_platform.Entity.SubscriptionPlan;

@Repository
public interface SubscritionPlanREpository extends JpaRepository<SubscriptionPlan,Long> {
	

}
