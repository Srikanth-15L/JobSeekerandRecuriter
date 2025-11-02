package com.example.job_platform.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.job_platform.Entity.Admin;
import com.example.job_platform.Enum.Role;

@Repository
public interface AdminReppository extends JpaRepository<Admin,Long> {
	
	List<Admin>findByRole(Role role);
	Optional<Admin>findById(Long id);

}
