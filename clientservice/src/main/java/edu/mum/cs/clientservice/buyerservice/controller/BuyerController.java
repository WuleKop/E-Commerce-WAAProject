package edu.mum.cs.clientservice.buyerservice.controller;


import edu.mum.cs.clientservice.buyerservice.BuyerService;
import edu.mum.cs.clientservice.sellermodel.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BuyerController {

    @Autowired
    private BuyerService buyerService;




    @GetMapping("/shop")
    public String getProductList(Model model){
        model.addAttribute("products",buyerService.allProducts());
        return "shop";
    }

    @GetMapping("shop/{productId}")
    public String productDetails(@PathVariable("productId")Long id,Model model){
        Product product =  buyerService.findOne(id);
        String [] images = product.getPictureUrls().split("\n");
        model.addAttribute("product",product);
        model.addAttribute("images",images);
        return "product-details";
    }


    @GetMapping("/carting")
    public String accessingeasly(Model model, HttpSession session){
        List<Product> products = (List<Product>) session.getAttribute("cart");
        model.addAttribute("cart",products);
        return  "cart";
    }


}
