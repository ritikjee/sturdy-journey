package com.ritikjha.companyMicroservice.repo;


import com.ritikjha.companyMicroservice.modal.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
