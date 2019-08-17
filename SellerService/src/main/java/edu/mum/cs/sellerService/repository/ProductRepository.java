package edu.mum.cs.sellerService.repository;

import edu.mum.cs.sellerService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> getAllBySellerId(Long pId);
}
