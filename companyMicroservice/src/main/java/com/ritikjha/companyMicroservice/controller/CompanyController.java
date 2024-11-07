package com.ritikjha.companyMicroservice.controller;



import com.ritikjha.companyMicroservice.modal.Company;
import com.ritikjha.companyMicroservice.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company,@PathVariable Long id) {
        companyService.updateCompany(company, id);
        return ResponseEntity.ok(companyService.updateCompany(company, id));
    }

    @PostMapping("/create")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        return new ResponseEntity<>(companyService.addCompany(company), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        if (!companyService.deleteCompany(id)) {
            return new ResponseEntity<>("Company with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Company with id " + id + " deleted successfully", HttpStatus.OK);
    }
}
