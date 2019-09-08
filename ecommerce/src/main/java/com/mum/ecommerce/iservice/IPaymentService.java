package com.mum.ecommerce.iservice;

import com.mum.ecommerce.admin.Account;
import com.mum.ecommerce.domain.Payment;

public interface IPaymentService {


    Payment addPayment(Payment payment);

    Payment clientPaymentMethod(Long accountId);


    String paying(Long Id,double amount);
}
