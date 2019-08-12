package edu.mum.cs.clientservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/product/prod_details")
    public String getProductDetail(){
        return "product-details";
    }

    @GetMapping("/checkout")
    public String checkout(){
        return "checkout";
    }

    @GetMapping("/cart")
    public String getCart(){
        return "cart";
    }
}
