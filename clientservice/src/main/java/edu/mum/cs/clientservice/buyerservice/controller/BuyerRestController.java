package edu.mum.cs.clientservice.buyerservice.controller;


import edu.mum.cs.clientservice.buyermodel.Cart;
import edu.mum.cs.clientservice.buyerservice.BuyerService;
import edu.mum.cs.clientservice.sellermodel.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BuyerRestController {


    @Autowired
    private BuyerService buyerService;


    @GetMapping("/products")
    public List<Product> allProducts() {

        return buyerService.allProducts();
    }

    @GetMapping("products/{productId}")
    public Product productDetails(@PathVariable("productId") Long id) {
        return buyerService.findOne(id);
    }

    @PostMapping("addingProduct")
    public List<Product> addingToCart(Product product, HttpSession session) {
        List<Product> products = new ArrayList<>();
        if (session.getAttribute("cart") != null) {
            products = (List<Product>) session.getAttribute("cart");

        }
        products.add(product);
        session.setAttribute("cart", products);
        return products;
    }


}
