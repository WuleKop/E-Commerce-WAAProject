package edu.mum.cs.clientservice.controller;

import edu.mum.cs.clientservice.adminmodel.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class ProductController {
    @GetMapping("/product/prod_details")
    public String getProductDetail(){
        return "product-details";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model, RedirectAttributes redirectAttributes){
        if(session.getAttribute("user") ==  null){
            redirectAttributes.addFlashAttribute("msg","Please sign or create account for checkount");
            return  "redirect:/logon";
        }
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        return "checkout";
    }

    @GetMapping("/cart")
    public String getCart(){
        return "cart";
    }
}
