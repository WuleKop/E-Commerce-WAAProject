package edu.mum.cs.AdminService.controller;

import edu.mum.cs.AdminService.model.Role;
import edu.mum.cs.AdminService.model.User;
import edu.mum.cs.AdminService.iservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "/login"})
    public String login(@RequestParam("user") User user) {
        return "login";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam("user") User user, Model model) {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public User createNewUser(@Valid User user) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            return null;
        } else {
            return userService.saveUser(user);
        }

    }

    @RequestMapping(value = "/seller", method = RequestMethod.POST)
    public User createNewUser(Object [] objects) {
        User userExists = userService.findUserByEmail(((User) objects[0]).getEmail());
        if (userExists != null) {
            return null;
        } else {

            return userService.saveUser((User)objects[0]);
        }

    }



    @RequestMapping(value = "/admin/adminHome", method = RequestMethod.GET)
    public User admin(@RequestParam("user") User user) {
        if (user.getRole().equals(Role.ADMIN)) {
            return userService.findUserByEmail(user.getEmail());
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/buyer/buyerHome", method = RequestMethod.GET)
    public User user(@RequestParam("user") User user) {
        if (user.getRole().equals(Role.BUYER)) {
            return userService.findUserByEmail(user.getEmail());
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/seller/sellerHome", method = RequestMethod.GET)
    public User seller(@RequestParam("user") User user, Model model) {
        if (user.getRole().equals(Role.SELLER)) {
            return userService.findUserByEmail(user.getEmail());
        } else {
            return null;
        }
    }


    @GetMapping("login/{email}")
    public User publicLogin(@PathVariable("email") String emailAddress) {
        User user = userService.findUserByEmail(emailAddress);
        if (user != null) {
            return user;
        }
        return null;
    }
}
