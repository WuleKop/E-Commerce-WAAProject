package com.mum.ecommerce.service;

import com.mum.ecommerce.EcommerceApplication;
import com.mum.ecommerce.dao.PaymentDao;
import com.mum.ecommerce.domain.Payment;
import com.mum.ecommerce.iservice.IPaymentService;
import com.netflix.discovery.converters.Auto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    private static final Logger logger = LogManager.getLogger(EcommerceApplication.class);

    @Autowired
    private PaymentDao paymentDao;



    @Override
    public Payment addPayment(Payment payment) {
        try {
            return paymentDao.save(payment);
        }catch (Exception e){
           logger.error(e);
        }
        return  null;
    }

    @Override
    public Payment clientPaymentMethod(Long accountId) {
        return  paymentDao.clientPaymentMethod(accountId);
    }

    @Override
    public String paying(Long Id,double amount) {
        try {
            Payment payment = paymentDao.clientPaymentMethod(Id);
            payment.setBalanceAmount(payment.getBalanceAmount() - amount);
            paymentDao.save(payment);
        }catch (Exception e){
            logger.error(e);
        }
        return  "Payment successfully made";
    }
}
