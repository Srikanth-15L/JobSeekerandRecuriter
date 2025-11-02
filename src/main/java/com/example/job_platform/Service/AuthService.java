package com.example.job_platform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.job_platform.DTO.AuthResponse;
import com.example.job_platform.DTO.LoginRequest;
import com.example.job_platform.DTO.RegisterRequest;
import com.example.job_platform.Entity.User;
import com.example.job_platform.Repository.UserRepository;
import com.example.job_platform.Security.JWTUtil;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	 public AuthResponse register(RegisterRequest request) {
		 User user = new User();
		 user.setName(request.name);
		 user.setEmail(request.email);
		 user.setPassword(passwordEncoder.encode(request.password));
		 user.setRole(request.role);
		 userRepository.save(user);
		 
		 String token = jwtUtil.generateToken( user.getEmail(),user.getRole().name());
		 return  new AuthResponse(token);
		 
		  
	 }
	 
	 
	 public AuthResponse login(LoginRequest request) {
		 
		 User user = userRepository.findByEmail(request.email).orElseThrow(() -> new RuntimeException("User not found"));
		 
		 
		 if(!passwordEncoder.matches(request.password, user.getPassword()))
			 throw new RuntimeException("Invalid credentials");
		 
		 String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
		 return new AuthResponse(token);
		 
		 
	 }
}
