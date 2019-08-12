package edu.mum.cs.clientservice.buyerservice.controller;


import edu.mum.cs.clientservice.buyerservice.BuyerService;
import edu.mum.cs.clientservice.sellermodel.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BuyerController {

    @Autowired
    private BuyerService buyerService;




    @GetMapping("products/{id}")
    public @ResponseBody Product getOne(@PathVariable("id") Long id){
        return buyerService.findOne(id);
    }


}
