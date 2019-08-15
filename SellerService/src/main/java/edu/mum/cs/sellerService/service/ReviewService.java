package edu.mum.cs.sellerService.service;

import edu.mum.cs.sellerService.model.Review;

import java.util.List;

public interface ReviewService {
    Review save(Review review, Long pId);
    void delete(Long id, Long pId);
    List<Review> getReviewByProductId(Long pId);
    List<Review> getUnapprovedReviews();
}
