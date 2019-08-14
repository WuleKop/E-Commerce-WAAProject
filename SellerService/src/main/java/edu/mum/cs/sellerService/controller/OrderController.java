package edu.mum.cs.sellerService.controller;

import edu.mum.cs.sellerService.model.Order;
import edu.mum.cs.sellerService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public Order addOrder(@RequestBody Order order) {
         return orderService.saveOrder(order);
    }

    @PostMapping("/addProductOrder/{pId}")
    public Order addProductOrder(@RequestBody Order order, @PathVariable Long pId ) {
        return orderService.save(order, pId);
    }

    @GetMapping("/getOrders/{pId}")
    public List<Order> getOrder(@PathVariable Long pId) {

        return orderService.getOrderByProductId(pId);
    }

    @DeleteMapping("/deleteOrder/{id}/{pId}")
    public void deleteOrder (@PathVariable Long id, @PathVariable Long pId) {
        orderService.delete(id, pId);
    }
}
