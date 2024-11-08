package com.ritikjha.reviewMicroservice.messaging;

import com.ritikjha.reviewMicroservice.dto.ReviewMessage;
import com.ritikjha.reviewMicroservice.modals.Review;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Review review) {
        ReviewMessage reviewMessage = new ReviewMessage(review.getId(), review.getTitle(), review.getDescription(), review.getRating(), review.getCompanyId());
        rabbitTemplate.convertAndSend("review-queue", reviewMessage);
    }


}
