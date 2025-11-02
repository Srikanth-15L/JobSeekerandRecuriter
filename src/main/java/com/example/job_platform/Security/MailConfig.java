package com.example.job_platform.Security;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		
		JavaMailSenderImpl sender= new JavaMailSenderImpl();
		sender.setHost("smtp.gamil.com");
		sender.setPort(587);
		sender.setUsername("Your-email@gmail.com");
		sender.setPassword("your-app-password");
		
		Properties props= sender.getJavaMailProperties();
		
		props.put("mail.transport.protocal", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		
		return sender;
	}
	
}
