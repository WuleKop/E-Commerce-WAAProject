package com.mum.ecommerce.seller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class Product {


    private Long Id;

    private String  name;

    private String description;

    @Temporal(TemporalType.DATE)
    private LocalDate manufacturedDate;

    @Temporal(TemporalType.DATE)
    private LocalDate expirationDate;

    private Integer stockQuantity;

    private Double price;

    private boolean approvalStatus;

    public Product(String name, String description, LocalDate manufacturedDate, LocalDate expirationDate, Integer stockQuantity, Double price) {
        this.name = name;
        this.description = description;
        this.manufacturedDate = manufacturedDate;
        this.expirationDate = expirationDate;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.approvalStatus = Boolean.FALSE;
    }
}

