package edu.mum.cs.sellerService.service;

import edu.mum.cs.sellerService.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product findById(Long id);
    Product update(Product product);
    void delete(Long id);
    List<Product> getAllProducts();
    List<Product> getAllProductBySeller(Long sId);

}
