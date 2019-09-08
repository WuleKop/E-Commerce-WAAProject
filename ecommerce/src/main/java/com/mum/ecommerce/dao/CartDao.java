package com.mum.ecommerce.dao;

import com.mum.ecommerce.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends JpaRepository<Cart,Long> {

    @Query("select c from Cart c where c.cartOwner=?1")
   public List<Cart> clientCarts(Long id);
}
