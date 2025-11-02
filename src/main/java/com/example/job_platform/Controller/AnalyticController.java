package com.example.job_platform.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.job_platform.DTO.ApplicationTrackDTO;
import com.example.job_platform.DTO.DashBoardDTO;
import com.example.job_platform.Service.AnalyticService;


@RestController
@RequestMapping("/api/dashBoard")
public class AnalyticController {
	
	
	@Autowired
	private AnalyticService analyticSevice;
	
	
	@GetMapping("/summery")
	
	public ResponseEntity<DashBoardDTO>getSummery(){
		return ResponseEntity.ok(analyticSevice.getSummery());
	}
	
	@GetMapping("/applications/weekly")
	public ResponseEntity<List<ApplicationTrackDTO>> getWeeklyTrackers(){
		return ResponseEntity.ok(analyticSevice.getWeeklyApplicationsTracker());
	}

}
