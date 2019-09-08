package com.mum.ecommerce.seller;


import com.mum.ecommerce.seller.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Order {


    private Long Id;

    private Date orderDate;

    private Integer quantity;

    private Date shippingDate;


    private String trackingNumber;



    public Order(Date orderDate, Integer quantity, Date shippingDate, String trackingNumber) {
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.shippingDate = shippingDate;
        this.trackingNumber = trackingNumber;
    }
}
