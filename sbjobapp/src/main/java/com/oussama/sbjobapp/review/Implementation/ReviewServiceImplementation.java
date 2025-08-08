package com.oussama.sbjobapp.review.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oussama.sbjobapp.review.Review;
import com.oussama.sbjobapp.review.ReviewRepository;
import com.oussama.sbjobapp.review.ReviewService;

@Service
public class ReviewServiceImplementation implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImplementation(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Implement the methods defined in ReviewService interface
    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }
}
