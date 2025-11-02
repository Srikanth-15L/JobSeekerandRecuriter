package com.example.job_platform.Controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.job_platform.DTO.PaidSubscriptionDTO;
import com.example.job_platform.Service.PaidSubscriptionService;


@RestController
@RequestMapping("/api/subscriptions")
public class PaidSubscriptionController {

	
	@Autowired
	private PaidSubscriptionService paidSubscriptionService;
	
	
	@PostMapping
	public ResponseEntity<PaidSubscriptionDTO> subscription(@RequestBody PaidSubscriptionDTO dto ){
		return ResponseEntity.ok(paidSubscriptionService.createSubscription(dto));
	}
	
	@GetMapping("/user/{userEmail}")
	public ResponseEntity<List<PaidSubscriptionDTO>>getSubscriptionByUserEmail(@PathVariable String userEmail){
		return ResponseEntity.ok(paidSubscriptionService.getSubscriptionByUserEmail(userEmail));
	}
	@GetMapping("user/{employeeId}")
	public ResponseEntity<List<PaidSubscriptionDTO>>getSubscriptionByEmailoyeeId(@PathVariable Long employeeId){
		return ResponseEntity.ok(paidSubscriptionService.getSubscriptionByEmailoyeeId(employeeId));
	}
	
	@GetMapping("/user/{recruiterId}")
	public ResponseEntity<List<PaidSubscriptionDTO>>getSubscriptionByRecruiterId(@PathVariable Long recruiterId){
		return ResponseEntity.ok(paidSubscriptionService.getSubscriptionByRecruiterId(recruiterId));
	}
	
	@PostMapping
	public ResponseEntity<List<PaidSubscriptionDTO>>getAll(@RequestBody PaidSubscriptionDTO dto){
		return ResponseEntity.ok(paidSubscriptionService.getAll());
	}
	
	@GetMapping("/invoice/{id}")
	public ResponseEntity<InputStreamResource>downLoadInvoice(@PathVariable Long id){
		
		ByteArrayInputStream invoice = paidSubscriptionService.generateInvoic(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "Inline; fileName=invoice.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(invoice) );
	}
}
