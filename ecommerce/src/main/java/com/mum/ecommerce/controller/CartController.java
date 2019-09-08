package com.mum.ecommerce.controller;


import com.mum.ecommerce.EcommerceApplication;
import com.mum.ecommerce.admin.Account;
import com.mum.ecommerce.domain.Cart;
import com.mum.ecommerce.iservice.ICartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    private static final Logger logger = LogManager.getLogger(EcommerceApplication.class);

    @Autowired
    private ICartService cartService;


    @PostMapping("/carts")
    public Cart newCart(Cart cart){
            return cartService.createCart(cart);

    }

    @PostMapping("/carts/update")
    public Cart updateCart(Cart cart){
        return  cartService.updateCart(cart);

    }

    @DeleteMapping("carts/delete/{id}")
    public String deleteCart(@PathVariable("id") Long cartid){
            Cart cart = cartService.findCart(cartid);
            return cartService.checkOutCart(cart);

    }

    @GetMapping("carts/{id}")
    public Cart cartDetails(Long id){
        return  cartService.findCart(id);
    }

    @GetMapping("{clientId}/cart")
    public Cart clientCarts(@PathVariable("clientId") Long clientId){
        List<Cart> carts = cartService.clientCarts(clientId);
        if(carts.size() > 0){
            return  carts.get(0);
        }
        return  null;
    }


}
