package com.ritikjha.jobMicroservice.repo;


import com.ritikjha.jobMicroservice.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
