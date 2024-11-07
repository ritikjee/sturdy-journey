package com.ritikjha.reviewMicroservice.services;


import com.ritikjha.reviewMicroservice.modals.Review;
import com.ritikjha.reviewMicroservice.repo.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    public boolean addReview(Long companyId,Review review) {
       if(companyId!=null){
           review.setCompanyId(companyId);
           reviewRepository.save(review);
           return true;
       }
       return false;
    }

    public Review getReviewById(Long companyId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null && review.getCompanyId()==companyId) {
            return review;
        }
        return null;
    }

    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        Review reviewToUpdate = reviewRepository.findById(reviewId).orElse(null);
        if (reviewToUpdate != null && reviewToUpdate.getCompanyId()==companyId) {
            reviewToUpdate.setRating(review.getRating());
            reviewToUpdate.setTitle(review.getTitle());
            reviewToUpdate.setDescription(review.getDescription());
            reviewRepository.save(reviewToUpdate);
            return true;
        }
        return false;
    }

    public boolean deleteReview(Long companyId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null && review.getCompanyId()==companyId) {
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }

}
