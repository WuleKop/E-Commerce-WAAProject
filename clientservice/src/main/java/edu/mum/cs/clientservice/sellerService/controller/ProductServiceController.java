package edu.mum.cs.clientservice.sellerService.controller;

import edu.mum.cs.clientservice.adminService.AdminService;
import edu.mum.cs.clientservice.sellerService.ProductService;
import edu.mum.cs.clientservice.sellermodel.Order;
import edu.mum.cs.clientservice.sellermodel.Product;
import edu.mum.cs.clientservice.sellermodel.Review;
import edu.mum.cs.clientservice.sellermodel.ShippingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ProductServiceController {

    @Autowired
    ProductService productService;

    @Autowired
    private AdminService adminService;

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
    @GetMapping("/getProductOrders/{pId}")
    public String getProductOrders(@PathVariable Long pId, Model model) {
        model.addAttribute("orders", productService.getProductOrders(pId));
        model.addAttribute("pId",pId);
        return "seller/productOrders";
    }
    @GetMapping("/updateOrder/{oId}/{pId}")
    private String updateOrder(@PathVariable Long oId, @PathVariable Long pId, Model model) {
        Order o = productService.getOrderById(oId);
        model.addAttribute("order",o);
        model.addAttribute("pId",pId);
        return "seller/updateOrder";
    }
    @PostMapping("/testUpdateOrder")
    public String updateOrder(@RequestParam Map<String,String> map, Model model ) {
        Order o = productService.getOrderById(Long.parseLong(map.get("oId")));
        o.setShippingStatus(ShippingStatus.valueOf(map.get("status")));
        productService.updateOrder(o);
        model.addAttribute("pId", Long.parseLong(map.get("pId")));
        return "seller/orderStatusUpdated";
    }
    @GetMapping("/deleteOrderFromProduct/{pId}/{oId}")
    public String deleteOrder(@PathVariable Long oId, @PathVariable Long pId) {
        productService.deleteOrder(pId, oId);
        return "redirect:/getProductOrders/"+pId;
    }
    @PostMapping("/addReview/{pId}")
    public void addReview(Review review, @PathVariable Long pId) {
        productService.addReview(review, pId);
    }


}