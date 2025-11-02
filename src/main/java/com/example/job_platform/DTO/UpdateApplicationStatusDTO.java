package com.example.job_platform.DTO;

import com.example.job_platform.Enum.ApplicationStatus;

public class UpdateApplicationStatusDTO {

	public Long applicatiionId;
	public ApplicationStatus status;
	
	
	public UpdateApplicationStatusDTO() {}
	public UpdateApplicationStatusDTO(Long applicatiionId,ApplicationStatus status) {
		this.applicatiionId=applicatiionId;
		this.status=status;
	}
	public Long getApplicatiionId() {
		return applicatiionId;
	}
	public void setApplicatiionId(Long applicatiionId) {
		this.applicatiionId = applicatiionId;
	}
	public ApplicationStatus getStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	
	
	
}
