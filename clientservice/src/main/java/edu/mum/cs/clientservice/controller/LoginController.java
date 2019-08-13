package edu.mum.cs.clientservice.controller;

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

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private BuyerService buyerService;

    @GetMapping("/logon")
    public String login(){
        return "customer-login";
    }

    @GetMapping("/logout")
    public String logout(Model model,HttpSession session){
        session.invalidate();
        return "redirect:/shop";
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> map,Model model,HttpSession session) {
        User user = clientService.login(map.get("email"));
        if(user != null) {
            if (MessageConverter.getMd5(map.get("password")).equals(user.getPassword())) {
                session.setAttribute("user", user);
                if (session.getAttribute("cart") == null) {
                    return "redirect:/shop";
                } else {
                    return "redirect:/checkout";
                }

            } else {
                session.invalidate();
                return "Invalid Password";

            }
        }else{
            return  "UNknown user";
        }

    }

}
