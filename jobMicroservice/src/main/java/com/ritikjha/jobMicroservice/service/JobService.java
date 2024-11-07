package com.ritikjha.jobMicroservice.service;


import com.ritikjha.jobMicroservice.dto.JobsWithCompanyDto;
import com.ritikjha.jobMicroservice.model.Company;
import com.ritikjha.jobMicroservice.model.Job;
import com.ritikjha.jobMicroservice.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
public class JobService {

    private final JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public JobsWithCompanyDto getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            String url = "http://COMPANYMICROSERVICE:8081/companies/" + job.getCompanyId();
            Company company = restTemplate.getForObject(url, Company.class);
            return new JobsWithCompanyDto(job, company);
        }

        return null;
    }

    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    public boolean updateJob(Long id, Job job) {
        try {
            Optional<Job> jobOptional = jobRepository.findById(id);
            if (jobOptional.isPresent()) {
                Job jobToUpdate = jobOptional.get();
                jobToUpdate.setTitle(job.getTitle());
                jobToUpdate.setDescription(job.getDescription());
                jobToUpdate.setMinSalary(job.getMinSalary());
                jobToUpdate.setMaxSalary(job.getMaxSalary());
                jobToUpdate.setLocation(job.getLocation());
                jobRepository.save(jobToUpdate);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteJob(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
