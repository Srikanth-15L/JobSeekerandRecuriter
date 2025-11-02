package com.example.job_platform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.job_platform.DTO.AdminDTO;
import com.example.job_platform.DTO.SystemStatusDTO;
import com.example.job_platform.DTO.UserStatusUpdateDTO;
import com.example.job_platform.Enum.Role;
import com.example.job_platform.Service.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
	
	
	@Autowired
	private AdminService adminService;
	
	
	
	@GetMapping("/users")
	public ResponseEntity<List<AdminDTO>>getAllUsers(){
		return ResponseEntity.ok(adminService.getAllUsers());
	}
	
	@GetMapping("users/role")
	public ResponseEntity<List<AdminDTO>>getUsersByRole(@RequestParam Role role){
		return ResponseEntity.ok(adminService.getUserByRole(role));
	}
	@PutMapping("/users/status")
	public ResponseEntity<AdminDTO> updateUserStatus(@RequestBody UserStatusUpdateDTO dto) {
		return ResponseEntity.ok(adminService.updateUserStatus(dto));
	}
	
	@GetMapping("/status")
	public ResponseEntity<SystemStatusDTO>getSystemStatus(){
		return ResponseEntity.ok(adminService.getSystemStatus());
	}

}
