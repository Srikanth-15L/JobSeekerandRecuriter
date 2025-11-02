package com.example.job_platform.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.job_platform.Service.FileUploadService;

@RestController
@RequestMapping("/api/uploadFiles")
public class FileUploadController {
	
	@Autowired
	private FileUploadService uploadFile;
	
	@PostMapping("/resume")
	public ResponseEntity<String>uploadResume(@RequestParam("file") MultipartFile file) throws IOException{
		return ResponseEntity.ok(uploadFile.uploadFile(file, "resume"));
	}
	
	@PostMapping("/image")
	public ResponseEntity<String>uploadCertificate(@RequestParam("file") MultipartFile file) throws IOException{
		return ResponseEntity.ok(uploadFile.uploadFile(file, "certificate"));
	}
	
	@PostMapping("/invoice")
	public ResponseEntity<String>invoice(@RequestParam("file") MultipartFile file) throws IOException{
		return ResponseEntity.ok(uploadFile.uploadFile(file, "invoce"));
	}

}
