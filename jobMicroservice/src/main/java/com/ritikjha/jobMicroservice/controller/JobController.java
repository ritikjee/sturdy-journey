package com.ritikjha.jobMicroservice.controller;


import com.ritikjha.jobMicroservice.dto.JobsWithCompanyDto;
import com.ritikjha.jobMicroservice.model.Job;
import com.ritikjha.jobMicroservice.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobsWithCompanyDto> getJobById(@PathVariable Long id) {
        JobsWithCompanyDto job = jobService.getJobById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Job> addJob(@RequestBody Job job) {
        jobService.addJob(job);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job) {
        if(jobService.updateJob(id, job)){
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
       if(jobService.deleteJob(id)){
           return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
       }
         return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
    }
}
