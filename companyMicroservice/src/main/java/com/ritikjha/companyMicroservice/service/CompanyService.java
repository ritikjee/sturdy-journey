package com.ritikjha.companyMicroservice.service;

import com.ritikjha.companyMicroservice.dto.ReviewMessage;
import com.ritikjha.companyMicroservice.modal.Company;
import com.ritikjha.companyMicroservice.repo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class CompanyService {

    private final CompanyRepository companyRepository;


    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company updateCompany(Company company, Long id) {
        Company companyToUpdate = companyRepository.findById(id).orElse(null);
        if (companyToUpdate != null) {
            companyToUpdate.setName(company.getName());
            companyToUpdate.setDescription(company.getDescription());
            companyRepository.save(companyToUpdate);
            return companyToUpdate;
        }
        return null;
    }

    public Company addCompany(Company company) {
        companyRepository.save(company);
        return company;
    }

    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public void updateCompany(ReviewMessage reviewMessage) {

    }
}
