package edu.mum.cs.clientservice.adminService.controller;

import edu.mum.cs.clientservice.adminService.AdminService;
import edu.mum.cs.clientservice.adminmodel.Status;
import edu.mum.cs.clientservice.adminmodel.User;
import edu.mum.cs.clientservice.sellermodel.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class adminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/home")
    public String manageSellers(Model model){
        model.addAttribute("users",adminService.peristedSellers());
        return "admin/admin_mainpanel";

    }
     @GetMapping("/admin/users")
    public String manageUsers(Model model){
            model.addAttribute("all",adminService.AllUsers());
            return "admin/allUsers";
    }
    @GetMapping("/admin/reviews")
    public String manageReviews(Model model) {
        model.addAttribute("all", adminService.AllUsers());
        return "admin/reviews";
    }


    @GetMapping("/approved/{email}")
    public String approveSellers(@PathVariable("email") String email){
        User user = adminService.login(email);
        user.setStatus(Status.APPROVED);
        adminService.approveSeller(user);
        return "redirect:/admin/home";
    }
    


}
