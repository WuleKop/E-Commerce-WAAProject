package edu.mum.cs.clientservice.buyerservice.controller;


import edu.mum.cs.clientservice.buyerservice.BuyerService;
import edu.mum.cs.clientservice.sellerService.ProductService;
import edu.mum.cs.clientservice.sellermodel.Order;
import edu.mum.cs.clientservice.sellermodel.Product;
import edu.mum.cs.clientservice.sellermodel.ProductOrder;
import edu.mum.cs.clientservice.utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private ProductService productService;




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


    @PostMapping("/carting")
    public String accessingeasly(Model model, HttpSession session, @RequestParam Map<String,String> map){
        List<ProductOrder> productOrders = (List<ProductOrder>) session.getAttribute("cart");
        if(productOrders == null){
            productOrders = new ArrayList<>();
        }
        Order order = (Order) session.getAttribute("order");
        if(order == null){
           order= new Order();
            order.setOrderNumber(UUID.randomUUID().toString().split("-")[0]);
        }
        Product product = productService.findOne(Long.parseLong(map.get("productId")));
        productOrders= buyerService.addProductOrder(product,order,Integer.parseInt(map.get("quantity")),productOrders);
        session.setAttribute("cart",productOrders);
        model.addAttribute("cart",productOrders);
        model.addAttribute("subtotal", UtilityClass.subTotal(productOrders));
        model.addAttribute("total",UtilityClass.subTotal(productOrders)+7);
        return  "cart";
    }


}
