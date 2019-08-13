package edu.mum.cs.clientservice.controller;

import edu.mum.cs.clientservice.adminmodel.User;
import edu.mum.cs.clientservice.sellermodel.ProductOrder;
import edu.mum.cs.clientservice.utility.UtilityClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

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
        List<ProductOrder> productOrderList = (List<ProductOrder>) session.getAttribute("cart");
        model.addAttribute("user",user);
        model.addAttribute("cart",productOrderList);
        model.addAttribute("subtotal", UtilityClass.subTotal(productOrderList));
        model.addAttribute("total",UtilityClass.subTotal(productOrderList)+7);
        return "checkout";
    }

    @GetMapping("/cart")
    public String getCart(){
        return "cart";
    }
}
