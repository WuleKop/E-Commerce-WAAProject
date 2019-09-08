package com.mum.ecommerce.dao;

import com.mum.ecommerce.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao extends JpaRepository<Payment,Long> {

  @Query("select p from Payment p where p.cardHolder=?1")
  public Payment clientPaymentMethod(Long id);
}
