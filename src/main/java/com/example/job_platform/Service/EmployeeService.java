package com.example.job_platform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.job_platform.DTO.EmployeeDTO;
import com.example.job_platform.Entity.Employee;
import com.example.job_platform.Repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeDTO getEmployeeByEmail(String email) {
		Employee emp= employeeRepository.findByEmail(email);
		if (emp==null)return null ;
			return new EmployeeDTO(
					emp.getId(),
					emp.getName(),
					emp.getEmail(),
					emp.getPhone(),
					emp.getQualification(),
					emp.getResumeURL());
		
	}
	public EmployeeDTO updateProfile(EmployeeDTO dto) {
		Employee emp= employeeRepository.findByEmail(dto.email);
		if (emp==null) {
			emp= new Employee();
			emp.setEmail(dto.email);
			
		}
	    
		emp.setId(dto.id);
		emp.setName(dto.name);
		emp.setEmail(dto.email);
		emp.setPhone(dto.phone);
		emp.setQualification(dto.qualification);
		emp.setResumeURL(dto.resumeURL);
		
		
		Employee saved = employeeRepository.save(emp);
		
		return new EmployeeDTO(
				saved.getId(),
				saved.getName(),
				saved.getEmail(),
				saved.getPhone(),
				saved.getQualification(),
				saved.getResumeURL());
	}

}
