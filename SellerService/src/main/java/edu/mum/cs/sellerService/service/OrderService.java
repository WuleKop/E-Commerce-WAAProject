package edu.mum.cs.sellerService.service;

import edu.mum.cs.sellerService.model.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order, Long pId);
    void delete(Long id, Long pId);
    List<Order> getOrderByProductId(Long pId);
}
