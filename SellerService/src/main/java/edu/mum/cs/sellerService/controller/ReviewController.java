package edu.mum.cs.sellerService.controller;

import edu.mum.cs.sellerService.model.Review;
import edu.mum.cs.sellerService.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/addReview/{pId}")
    public Review addReview(@RequestBody Review review, @PathVariable Long pId ) {
        return reviewService.save(review, pId);
    }

    @GetMapping("/getReviews/{pId}")
    public List<Review> getReview(@PathVariable Long pId) {
        
        return reviewService.getReviewByProductId(pId);
    }

    @DeleteMapping("/deleteReview/{id}/{pId}")
    public void deleteReview (@PathVariable Long id, @PathVariable Long pId) {
        reviewService.delete(id, pId);
    }
    @GetMapping("/getUnapprovedReviews")
    public List<Review> getUnapprovedReviews() {
        return reviewService.getUnapprovedReviews();
    }
    @PostMapping("/updateReview")
    public Review updateReview(@RequestBody Review review) {
        return reviewService.update(review);
    }
    @GetMapping("/getReviewById/{rId}")
    public Review getReviewsById(@PathVariable Long rId) {
        return reviewService.getById(rId);
    }


}
