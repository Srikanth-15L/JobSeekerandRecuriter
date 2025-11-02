package com.example.job_platform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.job_platform.DTO.EmailRequest;
import com.example.job_platform.Service.EmailService;

@RestController
@RequestMapping("/api/notifications")
public class EmailController {
	
	
	@Autowired
	private EmailService emailService;
	
	
	@PostMapping("/email")
	public ResponseEntity<String>sendEmail(@RequestBody EmailRequest request){
		return ResponseEntity.ok(emailService.sendEmail(request));
		
	}
	

}
