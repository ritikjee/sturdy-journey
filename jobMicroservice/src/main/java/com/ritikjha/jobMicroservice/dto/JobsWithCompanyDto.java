package com.ritikjha.jobMicroservice.dto;

import com.ritikjha.jobMicroservice.model.Company;
import com.ritikjha.jobMicroservice.model.Job;

public class JobsWithCompanyDto {
    private Job job;
    private Company company;

    public JobsWithCompanyDto() {
    }

    public JobsWithCompanyDto(Job job, Company company) {
        this.job = job;
        this.company = company;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
