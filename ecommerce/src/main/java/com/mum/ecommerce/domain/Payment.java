package com.mum.ecommerce.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Payment {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String cardNumber;

    private String cvv;

    private  String cardHolder;

    private LocalDate expirationDate;

    //account for the client who made purchase
    @Column(unique = true)
    private Long account;

    private double balanceAmount;
}
