package com.example.job_platform.Service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.job_platform.Security.CloudinaryConfig;

@Service
public class FileUploadService {
	
	@Autowired
	private CloudinaryConfig coludinaryConfig;
	
	@Autowired
	private Cloudinary cloudinary;
	
	
	
	
	public String uploadFile(MultipartFile file, String folder) throws IOException{
		try {
			Map uploadFile= coludinaryConfig.cloudinary().uploader().upload(file.getBytes(),ObjectUtils.asMap("folder",folder));
			
			return (String)uploadFile.get("secure_url");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 return null;
	}
	

}
