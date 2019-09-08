package com.mum.ecommerce.controller;


import com.mum.ecommerce.EcommerceApplication;
import com.mum.ecommerce.domain.Payment;
import com.mum.ecommerce.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private static final Logger logger = LogManager.getLogger(EcommerceApplication.class);

    @Autowired
    private PaymentService paymentService;


    @PostMapping("payments")
    public Payment addPayment(Payment payment){
             return paymentService.addPayment(payment);
    }

    @GetMapping("payments/{id}")
    public Payment findPayment(@PathVariable("id") Long id){
        return  paymentService.clientPaymentMethod(id);
    }


}
