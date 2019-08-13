package edu.mum.cs.clientservice.buyerservice.controller;


import edu.mum.cs.clientservice.buyermodel.Cart;
import edu.mum.cs.clientservice.buyerservice.BuyerService;
import edu.mum.cs.clientservice.sellermodel.Product;
import edu.mum.cs.clientservice.sellermodel.ProductOrder;
import edu.mum.cs.clientservice.utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("products/{productId}")
    public String removeProductFromCart(@PathVariable("productId") Long id, HttpSession session, Model model){
        List<ProductOrder> orders = (List<ProductOrder>) session.getAttribute("cart");
        orders = buyerService.removeProduct(orders,id);
        session.setAttribute("cart", orders);
        model.addAttribute("products",orders);
        return "Product is successfully removed";
    }


}
