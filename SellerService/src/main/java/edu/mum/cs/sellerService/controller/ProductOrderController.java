package edu.mum.cs.sellerService.controller;

import edu.mum.cs.sellerService.model.Order;
import edu.mum.cs.sellerService.model.Product;
import edu.mum.cs.sellerService.model.ProductOrder;
import edu.mum.cs.sellerService.service.IProductorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductOrderController {
    @Autowired
    private IProductorderService iProductorderService;

    @PostMapping("/purchaseOrders")
    public String addProductOrders(@RequestBody List<ProductOrder> productOrders) {
        return iProductorderService.addProductOrder(productOrders);
    }
    @GetMapping("/getProductOrders/{pId}")
    public List<Order> productOrders(@PathVariable Long pId) {
        List<ProductOrder> productOrders = iProductorderService.getProductOrderOfProduct(pId);
        List<Order> orders = productOrders.stream().map(p->p.getOrder()).collect(Collectors.toList());
        return orders;
    }
    @DeleteMapping("/deleteOrderFromProduct/{pId}/{oId}")
    public void deleteOrderFromProduct(@PathVariable Long pId, @PathVariable Long oId) {
        iProductorderService.deleteOrderFromProduct(pId, oId);
    }
}
