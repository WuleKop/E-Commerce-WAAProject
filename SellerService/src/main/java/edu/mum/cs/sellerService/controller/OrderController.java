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

    @GetMapping("/getOrder/{oId}")
    public Order getOrder(@PathVariable Long oId) {
       return orderService.getOrder(oId);
    }

    @DeleteMapping("/deleteOrder/{oId}")
    public void deleteOrder (@PathVariable Long oId) {
        orderService.delete(oId);
    }
    @GetMapping("/getOrdersByAccountId/{aId}")
    public List<Order> getOrdersByAccountId(@PathVariable Long aId) {
        return orderService.findOrdersByAccountId(aId);
    }
}
