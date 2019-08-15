package edu.mum.cs.clientservice.controller;

import edu.mum.cs.clientservice.adminmodel.Follower;
import edu.mum.cs.clientservice.adminmodel.User;
import edu.mum.cs.clientservice.buyerservice.BuyerService;
import edu.mum.cs.clientservice.sellerService.ProductService;
import edu.mum.cs.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

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
    public String getSellerDetail(@PathVariable("sellerEmail") String email, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        if (session.getAttribute("user") != null) {
            User seller = clientService.login(email);
            model.addAttribute("seller", seller);
            model.addAttribute("products", productService.sellerProducts(seller.getId()));
            return "seller-products";
        } else {
            redirectAttributes.addFlashAttribute("login", "Login first to follow a seller!!");
            return "redirect:/shop";
        }


    }

    @PostMapping("/follow/{sellerEmail}")
    public String follow(@PathVariable("sellerEmail") String email, HttpSession session) {
        System.out.println("This is the seller to follow: " + email);
        User buyer = (User) session.getAttribute("user");
        System.out.println("This is the follower :" + buyer.getEmail());
        User seller = clientService.login(email);
        Follower follower = new Follower();
        follower.setFollower(seller);
        follower.setFollowing(buyer);
        Follower following = clientService.follow(follower);
        return "redirect:/shop";

    }
}
