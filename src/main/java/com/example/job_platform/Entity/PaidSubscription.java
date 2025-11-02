package com.example.job_platform.Entity;

import java.time.LocalDate;

import javax.persistence.*;

import com.example.job_platform.Enum.PaymentStatus;

@Entity
@Table(name="paidSubcriptions")
public class PaidSubscription {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long id;
	
	private Long recruiterId;
	private Long employeeId;
	
	private Long planId;
	private Double amount;
	private String userEmail;
	private LocalDate startDate;
	private LocalDate endDate;
	private String currency;
	private PaymentStatus paymentStatus;
	private String invoiceUrl;
	
	
	public PaidSubscription() {}
	public PaidSubscription(Long id,Long recruiterId,Long employeeId,Long planId,String userEmail,String currency,LocalDate startDate,LocalDate endDate,Double amount,PaymentStatus paymentStatus,String invoiceUrl) {
		
		this.id=id;
		this.recruiterId=recruiterId;
		this.employeeId=employeeId;
		this.planId=planId;
		this.userEmail=userEmail;
		this.startDate=startDate;
		this.endDate=endDate;
		this.paymentStatus=paymentStatus;
		this.currency=currency;
		this.amount=amount;
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
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
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

	public String getInvoiceUrl() {
		return invoiceUrl;
	}

	public void setInvoiceUrl(String invoiceUrl) {
		this.invoiceUrl = invoiceUrl;
	}
	
	
	

}
