package com.oussama.sbjobapp.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.oussama.sbjobapp.company.Company;
import com.oussama.sbjobapp.company.CompanyService;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;
    private CompanyService companyService;

    public ReviewController(ReviewService reviewService, CompanyService companyService) {
        this.reviewService = reviewService;
        this.companyService = companyService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> getAllReviews(@PathVariable Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
        
        List<Review> reviews = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<?> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
        
        Review review = reviewService.getReviewById(companyId, reviewId);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isReviewSved = reviewService.addReview(companyId, review);
        if (isReviewSved) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review updatedReview) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
        
        boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, updatedReview);
        if (isReviewUpdated) {
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
        
        boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
        if (isReviewDeleted) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
    }

}
