package com.example.job_platform.DTO;

import java.time.LocalDate;

import com.example.job_platform.Enum.PaymentStatus;

public class PaidSubscriptionDTO {
	
	public Long id;
	
	public Long recruiterId;
	public Long employeeId;
	public Long planId;
	
	public String userEmail;
	public LocalDate startDate;
	public LocalDate endDate;
	public Double amount;
	public String currency;
	public PaymentStatus paymentStatus;
	public String invoiceUrl;
	
	
	public PaidSubscriptionDTO() {}
	public PaidSubscriptionDTO(Long id,Long recruiterId,Long employeeId,String currency,Long planId,Double amount,String userEmail,LocalDate startDate,LocalDate endDate,PaymentStatus paymentStatus,String invoiceUrl) {
		
		this.id=id;
		this.recruiterId=recruiterId;
		this.employeeId=employeeId;
		this.planId=planId;
		this.userEmail=userEmail;
		this.startDate=startDate;
		this.endDate=endDate;
		this.amount= amount;
		this.currency=currency;
		this.paymentStatus=paymentStatus;
		this.invoiceUrl=invoiceUrl;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(Long recruiterId) {
		this.recruiterId = recruiterId;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getInvoiceUrl() {
		return invoiceUrl;
	}
	public void setInvoiceUrl(String invoiceUrl) {
		this.invoiceUrl = invoiceUrl;
	}
	
	
	

}
