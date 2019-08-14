package edu.mum.cs.sellerService.controller;

import edu.mum.cs.sellerService.model.ProductOrder;
import edu.mum.cs.sellerService.service.IProductorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductOrderController {
    @Autowired
    private IProductorderService iProductorderService;

    @PostMapping("/purchaseOrders")
    public String addProductOrders(@RequestBody List<ProductOrder> productOrders) {
        return iProductorderService.addProductOrder(productOrders);
    }
}
