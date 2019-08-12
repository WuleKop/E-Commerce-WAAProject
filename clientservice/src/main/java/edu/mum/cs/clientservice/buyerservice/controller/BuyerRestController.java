package edu.mum.cs.clientservice.buyerservice.controller;


import edu.mum.cs.clientservice.buyermodel.Cart;
import edu.mum.cs.clientservice.buyerservice.BuyerService;
import edu.mum.cs.clientservice.sellermodel.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BuyerRestController {


    @Autowired
    private BuyerService buyerService;


    @GetMapping("/products")
    public List<Product> allProducts(){

        return  buyerService.allProducts();
    }

    @GetMapping("products/{productId}")
   public Product productDetails(@PathVariable("productId") Long id){
        return  buyerService.findOne(id);
   }
}
