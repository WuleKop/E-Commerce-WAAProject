package edu.mum.cs.clientservice.controller;

import edu.mum.cs.clientservice.adminmodel.User;
import edu.mum.cs.clientservice.buyerservice.BuyerService;
import edu.mum.cs.clientservice.sellerService.ProductService;
import edu.mum.cs.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SellerProductsController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @GetMapping("/seller/products")
    public String sellerProducts(Model model) {
        model.addAttribute("products", buyerService.allProducts());
        return "seller-products";
    }

    @GetMapping("/getSeller/{sellerEmail}")
    public String getSellerDetail(@PathVariable("sellerEmail") String email, Model model) {
        User seller= clientService.login(email);
        model.addAttribute("seller",seller );
        model.addAttribute("products", productService.sellerProducts(seller.getId()));
        return "seller-products";

    }
}
