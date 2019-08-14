package edu.mum.cs.clientservice.buyerservice.controller;


import edu.mum.cs.clientservice.adminmodel.User;
import edu.mum.cs.clientservice.buyerservice.BuyerService;
import edu.mum.cs.clientservice.sellerService.ProductService;
import edu.mum.cs.clientservice.sellermodel.Order;
import edu.mum.cs.clientservice.sellermodel.Product;
import edu.mum.cs.clientservice.sellermodel.ProductOrder;
import edu.mum.cs.clientservice.sellermodel.ShippingStatus;
import edu.mum.cs.clientservice.utility.ReportGenerating;
import edu.mum.cs.clientservice.utility.UtilityClass;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import java.util.*;

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
    public String accessingeasly(Model model, HttpSession session, @RequestParam Map<String,String> map, RedirectAttributes redirectAttributes){
        if(Integer.parseInt(map.get("quantity")) > 0) {
            List<ProductOrder> productOrders = (List<ProductOrder>) session.getAttribute("cart");
            if (productOrders == null) {
                productOrders = new ArrayList<>();
            }
            Order order = (Order) session.getAttribute("order");
            if (order == null) {
                order = new Order();
                order.setOrderNumber(UUID.randomUUID().toString().split("-")[0]);
            }

            Product product = productService.findOne(Long.parseLong(map.get("productId")));
            if(product.getStockQuantity() < Integer.parseInt(map.get("quantity"))){
                redirectAttributes.addFlashAttribute("Error" ,"Sorry ,no enough products in the stock");
                return "redirect:/shop/"+map.get("productId");
            }
            productOrders = buyerService.addProductOrder(product, order, Integer.parseInt(map.get("quantity")), productOrders);
            session.setAttribute("cart", productOrders);
            model.addAttribute("cart", productOrders);
            model.addAttribute("subtotal", UtilityClass.subTotal(productOrders));
            model.addAttribute("total", UtilityClass.subTotal(productOrders) + 7);
            return "cart";
        }
        redirectAttributes.addFlashAttribute("Error" ,"Choose at least one quantity");
        return "redirect:/shop/"+map.get("productId");

    }

    @PostMapping("/productOrders")
    public String placingOrder(@RequestParam Map<String,String> map,HttpSession session,Model model,RedirectAttributes redirectAttributes){
        try {
            User user = (User) session.getAttribute("user");
            List<ProductOrder> productOrders = (List<ProductOrder>) session.getAttribute("cart");
            Order oldOrder = productOrders.get(0).getOrder();
            oldOrder.setAccountId(user.getId());
            oldOrder.setQuantity(UtilityClass.totalQuantity(productOrders));
            oldOrder.setShippingStatus(ShippingStatus.New);
            oldOrder.setOrderDate(new Date());
            oldOrder.setTax(0.3);
            Order order = productService.postOrder(oldOrder);
            for (ProductOrder productOrder : productOrders) {
                productOrder.setOrder(order);
            }

            String result = productService.peristedProductorder(productOrders);
            redirectAttributes.addFlashAttribute("result","Order is successfully placed,please print the receipt info");

            return  "redirect:/shop";
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("sorry", "we are sorry we can't place your order now,try again later");
            return  "redirect:/checkout";
        }

    }

    @RequestMapping(value = "/receipt", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> taskReports(HttpSession session) throws Exception {

        List<ProductOrder> productOrders = (List<ProductOrder>) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        ByteArrayInputStream bytes = ReportGenerating.receipt(productOrders,productOrders.get(0).getOrder(),user);
        session.removeAttribute("cart");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", "inline; filename=projectTasks.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bytes));
    }


}
