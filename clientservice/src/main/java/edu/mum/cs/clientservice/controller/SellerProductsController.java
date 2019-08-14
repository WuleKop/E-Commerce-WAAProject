package edu.mum.cs.clientservice.controller;

import edu.mum.cs.clientservice.buyerservice.BuyerService;
import edu.mum.cs.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellerProductsController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping("/seller/products")
    public String sellerProducts(Model model){
        model.addAttribute("products",buyerService.allProducts());
        return "seller-products";
    }
}
