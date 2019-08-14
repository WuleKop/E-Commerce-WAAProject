package edu.mum.cs.sellerService.repository;

import edu.mum.cs.sellerService.model.Order;
import edu.mum.cs.sellerService.model.Product;
import edu.mum.cs.sellerService.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder,Long> {
    @Query("select p from ProductOrder p where p.product = ?1 ")
    List<ProductOrder> findAllProductOrdersOfProduct(Product product);
    @Query("select p from ProductOrder p where p.order = ?1 ")
    List<ProductOrder> getProductByOrderId(Order order);
}
