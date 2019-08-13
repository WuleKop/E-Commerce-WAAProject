package edu.mum.cs.clientservice.sellerService.controller;

import edu.mum.cs.clientservice.sellerService.ProductService;
import edu.mum.cs.clientservice.sellermodel.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductServiceController {

    @Autowired
    ProductService productService;

    @GetMapping("/uploadProduct")
    public String uploadProduct() {
        return "seller/productUpload";
    }
    @GetMapping("/getSellerProducts/{sId}")
    public String getAllProduct(@PathVariable Long sId, Model model) {
        model.addAttribute("products", productService.sellerProducts(sId));
        return "seller/allProducts";
    }
    @GetMapping("/getProduct/{pId}")
    public String geProduct(@PathVariable Long pId, Model model) {
        model.addAttribute("product",productService.findOne(pId));
        return "seller/productDetail";
    }
    @GetMapping("/update/{pId}")
    public String getUpdate(@PathVariable Long pId, Model model) {
        model.addAttribute("product", productService.findOne(pId));
        return "seller/updateProduct";
    }

}