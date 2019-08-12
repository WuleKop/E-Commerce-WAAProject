package edu.mum.cs.clientservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/product/productDetail")
    public String getProductDetail(){
        return "product-details";
    }
}
