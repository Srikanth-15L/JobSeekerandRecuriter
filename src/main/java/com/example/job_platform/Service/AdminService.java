package com.example.job_platform.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.job_platform.DTO.AdminDTO;
import com.example.job_platform.DTO.SystemStatusDTO;
import com.example.job_platform.DTO.UserStatusUpdateDTO;
import com.example.job_platform.Entity.Admin;
import com.example.job_platform.Enum.Role;
import com.example.job_platform.Repository.AdminReppository;

@Service
public class AdminService {
	
	@Autowired
	private AdminReppository adminRepository;
	
	
	
	public List<AdminDTO>getAllUsers(){
		return adminRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}
	public List<AdminDTO>getUserByRole(Role role){
		return adminRepository.findByRole(role).stream().map(this::toDTO).collect(Collectors.toList());
		
	}
	public AdminDTO updateUserStatus(UserStatusUpdateDTO dto) {
		
		Optional<Admin> optional=adminRepository.findById(dto.getUserId());
		if(!optional.isPresent()) {
				throw new  RuntimeException("user not found");
				}
		Admin user=optional.get();
		user.setActive(dto.active);
		adminRepository.save(user);
		
		return toDTO(user);
		
	}
	public SystemStatusDTO getSystemStatus() {
		int totalUsers=(int)adminRepository.count();
		int totalEmployees=(int)adminRepository.count();
		int totalRecruiters=(int)adminRepository.count();
		int totalJobPosts=(int)adminRepository.count();
		int totalApplications=(int)adminRepository.count();
		
		SystemStatusDTO status = new SystemStatusDTO();
		status.setTotalUsers(totalUsers);
		status.setTotalEmployees(totalEmployees);
		status.setTotlRecruiters(totalRecruiters);
		status.setTotalJobPosts(totalJobPosts);
		status.setTotalApplicatiions(totalApplications);
		
		return status;
	}
	
	private AdminDTO toDTO(Admin admin) {
		AdminDTO dto =new AdminDTO();
		dto.setId(admin.getId());
		dto.setEmail(admin.getEmail());
		dto.setRole(admin.getRole());
		dto.setActive(admin.isActive());
		
		return dto;
		
	}
	
	


}
