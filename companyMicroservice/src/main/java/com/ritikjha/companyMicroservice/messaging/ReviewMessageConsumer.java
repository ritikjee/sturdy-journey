package com.ritikjha.companyMicroservice.messaging;

import com.ritikjha.companyMicroservice.dto.ReviewMessage;
import com.ritikjha.companyMicroservice.service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class ReviewMessageConsumer {
    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "review-queue")
    public void receiveMessage(ReviewMessage reviewMessage) {
        companyService.updateCompany(reviewMessage);
    }
}
