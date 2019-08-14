package edu.mum.cs.clientservice.sellerService.controller;

import edu.mum.cs.clientservice.sellerService.ProductService;
import edu.mum.cs.clientservice.sellermodel.Product;
import edu.mum.cs.clientservice.sellermodel.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        Product product = productService.findOne(pId);
        String [] images = product.getPictureUrls().split("\n");
        model.addAttribute("product",product);
        model.addAttribute("images",images);
        return "seller/productDetail";
    }
    @GetMapping("/updateProduct/{pId}")
    public String getUpdate(@PathVariable Long pId, Model model) {
        model.addAttribute("product", productService.findOne(pId));
        return "seller/updateProduct";
    }
    @GetMapping("/delete/{pId}")
    public String deleteProduct(@PathVariable Long pId, Model model) {
        Product p = productService.findOne(pId);
        productService.deleteProduct(pId);
        return "redirect:/getSellerProducts/"+p.getSellerId();
    }
    @GetMapping("/getReviews/{pId}")
    public String getReviews(@PathVariable Long pId, Model model) {
       model.addAttribute("reviews", productService.getReviews(pId));
       return "seller/productReviews";
    }

}