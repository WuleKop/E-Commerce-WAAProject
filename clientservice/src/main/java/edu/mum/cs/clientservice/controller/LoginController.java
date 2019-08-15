package edu.mum.cs.clientservice.controller;

import edu.mum.cs.clientservice.adminmodel.Role;
import edu.mum.cs.clientservice.adminmodel.User;
import edu.mum.cs.clientservice.buyerservice.BuyerService;
import edu.mum.cs.clientservice.service.ClientService;
import edu.mum.cs.clientservice.utility.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private BuyerService buyerService;

    @GetMapping("/logon")
    public String login(HttpSession session){
        return "customer-login";
    }

    @GetMapping("/")
    public  String welcome(){
        return "redirect:/shop";
    }

    @GetMapping("/test")
    public String test(){
        return "testcorausel";
    }

    @GetMapping("/logout")
    public String logout(Model model,HttpSession session){
        session.invalidate();
        return "redirect:/shop";
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> map, Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        if (map.get("email") == null || map.get("email") == "" || map.get("password") == null || map.get("password") == "") {
            model.addAttribute("error", "Please fill required fields");
           // return "redirect:/logon";
            return "customer-login";
        } else {
            User user = clientService.login(map.get("email"));
            // User user=new User();

            if (user != null) {
                if (MessageConverter.getMd5(map.get("password")).equals(user.getPassword())) {
                    session.setAttribute("user", user);
                    if (user.getRole().toString().equals(Role.BUYER.toString())) {
                        if (session.getAttribute("cart") == null) {
                            return "redirect:/shop";
                        } else {
                            return "redirect:/checkout";
                        }
                    } else if (user.getRole().toString().equals(Role.SELLER.toString())) {

                        return "redirect:/getSellerProducts/" + user.getId();
                    } else {
                        System.out.println("first");
                        redirectAttributes.addFlashAttribute("error", "Unknown user role");
                        return "redirect:/logon";
                    }

                } else {
                    redirectAttributes.addFlashAttribute("error", "Invalid username or password");
                    return "redirect:/logon";

                }
            } else {
                if(map.get("email").equals("ADMIN") && map.get("password").equals("ADMIN123")){
                    return "redirect:/admin/home";
                }
                redirectAttributes.addFlashAttribute("error", "Invalid username or password");
                return "redirect:/logon";
            }

        }
    }

}
