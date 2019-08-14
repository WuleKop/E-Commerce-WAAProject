package edu.mum.cs.sellerService.service;

import edu.mum.cs.sellerService.model.Order;

import java.util.List;

public interface OrderService {
    Order getOrder(Long id);
    Order saveOrder(Order order);
    void delete(Long id);
  }
