package edu.mum.cs.sellerService.repository;

import edu.mum.cs.sellerService.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.status = 'NEW'")
    List<Review> getUnapprovedReviews();
}
