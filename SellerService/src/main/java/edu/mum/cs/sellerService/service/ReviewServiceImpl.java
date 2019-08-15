package edu.mum.cs.sellerService.service;

import edu.mum.cs.sellerService.model.Product;
import edu.mum.cs.sellerService.model.Review;
import edu.mum.cs.sellerService.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Review save(Review review, Long pId) {
        Product p = productService.findById(pId);
        p.getReviews().add(review);
        return reviewRepository.save(review);
    }

    @Override
    public void delete(Long id, Long pId) {
        Review r = reviewRepository.findById(id).get();
        Product p = productService.findById(pId);
        p.getReviews().remove(r);
       reviewRepository.deleteById(id);
    }
    @Override
    public List<Review> getReviewByProductId(Long pId) {
        return productService.findById(pId).getReviews();
    }

    @Override
    public List<Review> getUnapprovedReviews() {
        return reviewRepository.getUnapprovedReviews();
    }
}
