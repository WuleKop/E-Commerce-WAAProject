package com.mum.ecommerce.domain;


import com.mum.ecommerce.seller.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;



    private LocalDate cartDate;


    //orderId for reusing it
    private Long orderId;

    //the owner id of the cart
    private Long cartOwner;

    @Transient
    private Order order;

    //for the sake of keeping the total amount of the order placed on this cart
    private double  totalAmount;




}
