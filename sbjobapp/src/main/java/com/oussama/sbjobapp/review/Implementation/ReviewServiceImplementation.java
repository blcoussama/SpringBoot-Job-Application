package com.oussama.sbjobapp.review.Implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oussama.sbjobapp.company.Company;
import com.oussama.sbjobapp.company.CompanyService;
import com.oussama.sbjobapp.review.Review;
import com.oussama.sbjobapp.review.ReviewRepository;
import com.oussama.sbjobapp.review.ReviewService;

@Service
public class ReviewServiceImplementation implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImplementation(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    // Implement the methods defined in ReviewService interface
    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return null; // Company not found
        }
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null); // Return null if review not found
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true; // Review added successfully
        } else {
            return false; // Company not found
        }
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return false; // Company not found
        }
        
        Review existingReview = getReviewById(companyId, reviewId);
        if (existingReview != null) {
            existingReview.setTitle(updatedReview.getTitle());
            existingReview.setDescription(updatedReview.getDescription());
            existingReview.setRating(updatedReview.getRating());
            reviewRepository.save(existingReview);
            return true; // Review updated successfully
        } else {
            return false; // Review not found
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return false; // Company not found
        }
        
        Review existingReview = getReviewById(companyId, reviewId);
        if (existingReview != null) {
            reviewRepository.delete(existingReview);
            return true; // Review deleted successfully
        } else {
            return false; // Review not found
        }
    }

}
