package edu.mum.cs.sellerService.service;

import edu.mum.cs.sellerService.model.Order;
import edu.mum.cs.sellerService.model.Product;
import edu.mum.cs.sellerService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order save(Order order, Long pId) {
        Product p = productService.findById(pId);
        p.addOrder(order);
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id, Long pId) {
        Order o = orderRepository.findById(id).get();
        Product p = productService.findById(pId);
        p.getOrders().remove(o);
        orderRepository.deleteById(id);

    }

    @Override
    public List<Order> getOrderByProductId(Long pId) {
        return productService.findById(pId).getOrders();
    }
}
