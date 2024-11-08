package com.ritikjha.jobMicroservice.client;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "company-service")
public interface CompanyClient {
}
